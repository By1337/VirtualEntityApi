package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;
import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.geom.Vec3s;

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
        private static final int PACKET_ID = PacketType.MOVE_ENTITY_PACKET_ROT.getId();

        public Rot(VirtualEntity entity) {
            super(entity);
        }

        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            byteBuf.writeByte(entity.yaw());
            byteBuf.writeByte(entity.pitch());
            byteBuf.writeBoolean(entity.isOnGround());
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
        private static final int PACKET_ID = PacketType.MOVE_ENTITY_PACKET_POS.getId();

        public Pos(VirtualEntity entity) {
            super(entity);
        }


        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            Vec3d deltaD = entity.getPos().mul(4096).sub(entity.getOldPos().mul(4096));
            Vec3s delta = new Vec3s(deltaD.x, deltaD.y, deltaD.z);
            byteBuf.writeShort(delta.x);
            byteBuf.writeShort(delta.y);
            byteBuf.writeShort(delta.z);
            byteBuf.writeBoolean(entity.isOnGround());
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
        private static final int PACKET_ID = PacketType.MOVE_ENTITY_PACKET_POS_ROT.getId();

        public PosRot(VirtualEntity entity) {
            super(entity);
        }


        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            Vec3d deltaD = entity.getPos().mul(4096).sub(entity.getOldPos().mul(4096));
            Vec3s delta = new Vec3s(deltaD.x, deltaD.y, deltaD.z);
            byteBuf.writeShort(delta.x);
            byteBuf.writeShort(delta.y);
            byteBuf.writeShort(delta.z);
            byteBuf.writeByte(entity.yaw());
            byteBuf.writeByte(entity.pitch());
            byteBuf.writeBoolean(entity.isOnGround());
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
}
