package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class TeleportEntityPacket extends Packet {
    private static final int PACKET_ID = Mappings.getPacketId(PacketType.TELEPORT_ENTITY_PACKET);
    private final VirtualEntity virtualEntity;

    public TeleportEntityPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        // https://wiki.vg/index.php?title=Protocol&oldid=16681#Entity_Teleport
        ByteBuffCodecs.VAR_INT.accept(PACKET_ID, byteBuf);
        ByteBuffCodecs.VAR_INT.accept(virtualEntity.getId(), byteBuf);
        byteBuf.writeDouble(virtualEntity.getPos().x);
        byteBuf.writeDouble(virtualEntity.getPos().y);
        byteBuf.writeDouble(virtualEntity.getPos().z);
        byteBuf.writeByte(virtualEntity.yaw());
        byteBuf.writeByte(virtualEntity.pitch());
        byteBuf.writeBoolean(virtualEntity.isOnGround());
    }
}
