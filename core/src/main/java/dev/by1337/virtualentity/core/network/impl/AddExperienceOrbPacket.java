package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.VirtualExperienceOrb;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

@RemovedInMinecraftVersion("1.21.5")
public class AddExperienceOrbPacket extends Packet {
    private static final int PACKET_ID = PacketType.ADD_EXPERIENCE_ORB_PACKET.getId();
    private final VirtualExperienceOrb virtualEntity;

    public AddExperienceOrbPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = (VirtualExperienceOrb) virtualEntity;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(virtualEntity.getId(), byteBuf);
        byteBuf.writeDouble(virtualEntity.getPos().x);
        byteBuf.writeDouble(virtualEntity.getPos().y);
        byteBuf.writeDouble(virtualEntity.getPos().z);
        byteBuf.writeShort(virtualEntity.value());
    }

    @Override
    public String toString() {
        return "AddExperienceOrbPacket{}";
    }
}
