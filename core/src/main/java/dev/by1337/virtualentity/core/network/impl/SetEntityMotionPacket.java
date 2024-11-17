package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;
import org.by1337.blib.geom.Vec3d;

public class SetEntityMotionPacket extends Packet {
    private static final int PACKET_ID = PacketType.SET_ENTITY_MOTION_PACKET.getId();

    private final int id;
    private final Vec3d motion;

    public SetEntityMotionPacket(int id, Vec3d motion) {
        this.id = id;
        this.motion = motion;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBuffUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBuffUtil.writeVarInt(id, byteBuf);
        byteBuf.writeShort(toUnit(clamp(motion.x, -3.9, 3.9)));
        byteBuf.writeShort(toUnit(clamp(motion.y, -3.9, 3.9)));
        byteBuf.writeShort(toUnit(clamp(motion.z, -3.9, 3.9)));
    }

    private static short toUnit(double d) {
        return (short) (d * 8000D);
    }

    public static double clamp(double source, double min, double max) {
        return source < min ? min : Math.min(source, max);
    }
}
