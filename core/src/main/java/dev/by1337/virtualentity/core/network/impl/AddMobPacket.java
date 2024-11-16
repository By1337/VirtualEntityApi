package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class AddMobPacket extends Packet {
    private final VirtualEntity virtualEntity;

    public AddMobPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }

    @Override
    protected void write(ByteBuf byteBuf) {
        ByteBuffCodecs.VAR_INT.accept(Mappings.getPacketId(PacketType.ADD_MOB_PACKET), byteBuf);
        ByteBuffCodecs.VAR_INT.accept(virtualEntity.getId(), byteBuf);
        ByteBuffCodecs.UUID_CODEC.accept(virtualEntity.getUuid(), byteBuf);
        ByteBuffCodecs.VAR_INT.accept(Mappings.getNetworkId(virtualEntity.getType()), byteBuf);
        byteBuf.writeDouble(virtualEntity.getPos().x);
        byteBuf.writeDouble(virtualEntity.getPos().y);
        byteBuf.writeDouble(virtualEntity.getPos().z);
        byteBuf.writeByte(virtualEntity.yaw());
        byteBuf.writeByte(virtualEntity.pitch());
        byteBuf.writeInt(virtualEntity.yaw()); // хз здесь должен быть yHeadRot
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
    }
}
