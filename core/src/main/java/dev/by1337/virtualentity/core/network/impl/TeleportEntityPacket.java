package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class TeleportEntityPacket extends Packet {
    private static final int PACKET_ID = PacketType.TELEPORT_ENTITY_PACKET.getId();
    private final VirtualEntity virtualEntity;

    public TeleportEntityPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(virtualEntity.getId(), byteBuf);
        byteBuf.writeDouble(virtualEntity.getPos().x);
        byteBuf.writeDouble(virtualEntity.getPos().y);
        byteBuf.writeDouble(virtualEntity.getPos().z);
        byteBuf.writeByte(virtualEntity.yaw());
        byteBuf.writeByte(virtualEntity.pitch());
        byteBuf.writeBoolean(virtualEntity.isOnGround());
    }
    @Override
    public String toString() {
        return "TeleportEntityPacket{" + virtualEntity.getType() + "}";
    }
}
