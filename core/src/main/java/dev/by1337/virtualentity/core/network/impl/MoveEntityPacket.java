package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;
import org.by1337.blib.geom.Vec3d;

public abstract class MoveEntityPacket extends Packet {
    protected final VirtualEntity entity;

    public MoveEntityPacket(VirtualEntity entity) {
        this.entity = entity;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(getPacketId(), byteBuf);
        ByteBufUtil.writeVarInt(entity.getId(), byteBuf);
    }

    protected abstract int getPacketId();

    public static class Rot extends MoveEntityPacket {
        private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:move_entity_rot");

        public Rot(VirtualEntity entity) {
            super(entity);
        }

        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            if (ServerVersion.CURRENT_PROTOCOL == 777) {
                byteBuf.writeBoolean(entity.isOnGround());
            }
            byteBuf.writeByte(entity.yaw());
            byteBuf.writeByte(entity.pitch());
            if (ServerVersion.CURRENT_PROTOCOL != 777) {
                byteBuf.writeBoolean(entity.isOnGround());
            }
        }

        @Override
        protected int getPacketId() {
            return PACKET_ID;
        }

        @Override
        public String toString() {
            return "MoveEntityPacket$Rot{}";
        }
    }

    public static class Pos extends MoveEntityPacket {
        private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:move_entity_pos");

        public Pos(VirtualEntity entity) {
            super(entity);
        }

        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            writePositionDelta(byteBuf, entity);
            if (ServerVersion.CURRENT_PROTOCOL != 777) {
                byteBuf.writeBoolean(entity.isOnGround());
            }
        }

        @Override
        protected int getPacketId() {
            return PACKET_ID;
        }
        @Override
        public String toString() {
            return "MoveEntityPacket$Pos{}";
        }
    }

    public static class PosRot extends MoveEntityPacket {
        private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:move_entity_pos_rot");

        public PosRot(VirtualEntity entity) {
            super(entity);
        }

        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            writePositionDelta(byteBuf, entity);
            byteBuf.writeByte(entity.yaw());
            byteBuf.writeByte(entity.pitch());
            if (ServerVersion.CURRENT_PROTOCOL != 777) {
                byteBuf.writeBoolean(entity.isOnGround());
            }
        }

        @Override
        protected int getPacketId() {
            return PACKET_ID;
        }
        @Override
        public String toString() {
            return "MoveEntityPacket$PosRot{}";
        }
    }

    private static void writePositionDelta(ByteBuf byteBuf, VirtualEntity entity) {
        if (ServerVersion.CURRENT_PROTOCOL == 777) {
            // packProperties(onGround, 0): a linear VecDelta has no intermediate steps.
            ByteBufUtil.writeVarInt(entity.isOnGround() ? 1 : 0, byteBuf);
        }
        Vec3d pos = entity.getPos();
        Vec3d oldPos = entity.getOldPos();
        // VecDeltaCodec quantizes each endpoint before subtracting them.
        byteBuf.writeShort((int) (Math.round(pos.x * 4096) - Math.round(oldPos.x * 4096)));
        byteBuf.writeShort((int) (Math.round(pos.y * 4096) - Math.round(oldPos.y * 4096)));
        byteBuf.writeShort((int) (Math.round(pos.z * 4096) - Math.round(oldPos.z * 4096)));
    }
}
