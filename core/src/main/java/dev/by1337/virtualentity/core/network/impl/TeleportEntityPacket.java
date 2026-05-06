package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;
import org.by1337.blib.util.Version;

public class TeleportEntityPacket extends Packet {
    private static final boolean IS_1_21_2_OR_NEWER = ServerVersion.CURRENT_PROTOCOL >= ServerVersion.Protocol.V1_21_2;
    private static final int OLD_TELEPORT_PAKET_ID = Packets.play.clientbound.getId("minecraft:teleport_entity");
    private static final int POS_SYNC_PACKET_ID = Packets.play.clientbound.getId("minecraft:entity_position_sync");
    private final VirtualEntity virtualEntity;

    public TeleportEntityPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        if (IS_1_21_2_OR_NEWER) {
            ByteBufUtil.writeVarInt(POS_SYNC_PACKET_ID, byteBuf);
            ByteBufUtil.writeVarInt(virtualEntity.getId(), byteBuf);
            byteBuf.writeDouble(virtualEntity.getPos().x);
            byteBuf.writeDouble(virtualEntity.getPos().y);
            byteBuf.writeDouble(virtualEntity.getPos().z);
            byteBuf.writeDouble(0); // deltaX
            byteBuf.writeDouble(0); // deltaY
            byteBuf.writeDouble(0); // deltaZ
            byteBuf.writeFloat(virtualEntity.getYaw());
            byteBuf.writeFloat(clamp(virtualEntity.getPitch() % 360.0F, -90.0F, 90.0F));
            byteBuf.writeBoolean(virtualEntity.isOnGround());
        } else {
            ByteBufUtil.writeVarInt(OLD_TELEPORT_PAKET_ID, byteBuf);
            ByteBufUtil.writeVarInt(virtualEntity.getId(), byteBuf);
            byteBuf.writeDouble(virtualEntity.getPos().x);
            byteBuf.writeDouble(virtualEntity.getPos().y);
            byteBuf.writeDouble(virtualEntity.getPos().z);
            byteBuf.writeByte(virtualEntity.yaw());
            byteBuf.writeByte(virtualEntity.pitch());
            byteBuf.writeBoolean(virtualEntity.isOnGround());
        }
    }
    public static float clamp(float value, float min, float max) {
        return Math.min(max, Math.max(value, min));
    }

    @Override
    public String toString() {
        return "TeleportEntityPacket{" + virtualEntity.getType() + "}";
    }
}
