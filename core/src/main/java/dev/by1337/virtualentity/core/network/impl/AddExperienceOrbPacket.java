package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.VirtualExperienceOrb;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class AddExperienceOrbPacket extends Packet {
    private final VirtualExperienceOrb virtualEntity;

    public AddExperienceOrbPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = (VirtualExperienceOrb) virtualEntity;
    }

    @Override
    protected void write(ByteBuf byteBuf) {
        ByteBuffCodecs.VAR_INT.accept(Mappings.getPacketId(PacketType.ADD_EXPERIENCE_ORB_PACKET), byteBuf);
        ByteBuffCodecs.VAR_INT.accept(virtualEntity.getId(), byteBuf);
        byteBuf.writeDouble(virtualEntity.getPos().x);
        byteBuf.writeDouble(virtualEntity.getPos().y);
        byteBuf.writeDouble(virtualEntity.getPos().z);
        byteBuf.writeShort(virtualEntity.value());
    }
}
