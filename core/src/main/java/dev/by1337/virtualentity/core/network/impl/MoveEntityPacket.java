package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
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
        ByteBuffCodecs.VAR_INT.accept(getPacketId(), byteBuf);
        ByteBuffCodecs.VAR_INT.accept(entity.getId(), byteBuf);
    }

    protected abstract int getPacketId();

    public static class Rot extends MoveEntityPacket {
        private static final int PACKET_ID = Mappings.getPacketId(PacketType.MOVE_ENTITY_PACKET_ROT);

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
    }

    public static class Pos extends MoveEntityPacket {
        private static final int PACKET_ID = Mappings.getPacketId(PacketType.MOVE_ENTITY_PACKET_POS);

        public Pos(VirtualEntity entity) {
            super(entity);
        }


        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            // https://wiki.vg/Protocol#Update_Entity_Position
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
    }

    public static class PosRot extends MoveEntityPacket {
        private static final int PACKET_ID = Mappings.getPacketId(PacketType.MOVE_ENTITY_PACKET_POS_ROT);

        public PosRot(VirtualEntity entity) {
            super(entity);
        }


        @Override
        public void write(ByteBuf byteBuf) {
            super.write(byteBuf);
            // https://wiki.vg/index.php?title=Protocol&oldid=16681#Entity_Position_and_Rotation
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
    }
}
