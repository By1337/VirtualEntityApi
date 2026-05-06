package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;
import org.by1337.blib.geom.Vec3d;

public class SetEntityMotionPacket extends Packet {
    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:set_entity_motion");

    private final int id;
    private final Vec3d motion;

    public SetEntityMotionPacket(int id, Vec3d motion) {
        this.id = id;
        this.motion = motion;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
        if (ServerVersion.CURRENT_PROTOCOL >= ServerVersion.Protocol.V1_21_9) {
            writeLpVec3(byteBuf, motion);
        } else {
            byteBuf.writeShort(toUnit(clamp(motion.x, -3.9, 3.9)));
            byteBuf.writeShort(toUnit(clamp(motion.y, -3.9, 3.9)));
            byteBuf.writeShort(toUnit(clamp(motion.z, -3.9, 3.9)));
        }
    }

    private static short toUnit(double d) {
        return (short) (d * 8000D);
    }

    public static double clamp(double source, double min, double max) {
        return source < min ? min : Math.min(source, max);
    }

    public static void writeLpVec3(ByteBuf buffer, Vec3d vector) {
        double d = sanitize(vector.x);
        double d1 = sanitize(vector.y);
        double d2 = sanitize(vector.z);
        double max = absMax(d, absMax(d1, d2));
        if (max < 3.051944088384301E-5) {
            buffer.writeByte(0);
        } else {
            long l = ceilLong(max);
            boolean flag = (l & 3L) != l;
            long l1 = flag ? l & 3L | 4L : l;
            long l2 = pack(d / (double) l) << 3;
            long l3 = pack(d1 / (double) l) << 18;
            long l4 = pack(d2 / (double) l) << 33;
            long l5 = l1 | l2 | l3 | l4;
            buffer.writeByte((byte) ((int) l5));
            buffer.writeByte((byte) ((int) (l5 >> 8)));
            buffer.writeInt((int) (l5 >> 16));
            if (flag) {
                ByteBufUtil.writeVarInt((int) (l >> 2), buffer);
            }
        }
    }

    public static long ceilLong(double value) {
        long l = (long) value;
        return value > (double) l ? l + 1L : l;
    }

    public static double absMax(double x, double y) {
        return Math.max(Math.abs(x), Math.abs(y));
    }

    private static double sanitize(double value) {
        return Double.isNaN(value) ? (double) 0.0F : clamp(value, -1.7179869183E10, 1.7179869183E10);
    }

    private static long pack(double coord) {
        return Math.round((coord * (double) 0.5F + (double) 0.5F) * (double) 32766.0F);
    }

    @Override
    public String toString() {
        return "SetEntityMotionPacket{" +
                "id=" + id +
                ", motion=" + motion +
                '}';
    }
}
