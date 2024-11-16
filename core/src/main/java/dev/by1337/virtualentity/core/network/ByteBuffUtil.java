package dev.by1337.virtualentity.core.network;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class ByteBuffUtil {
    private static final int SEGMENT_BITS = 0x7F;
    private static final int CONTINUE_BIT = 0x80;

    public static int readVarInt(ByteBuf byteBuf) {
        int value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = byteBuf.readByte();
            value |= (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 32) throw new RuntimeException("VarInt is too big");
        }

        return value;
    }

    public static long readVarLong(ByteBuf byteBuf) {
        long value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = byteBuf.readByte();
            value |= (long) (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 64) throw new RuntimeException("VarLong is too big");
        }

        return value;
    }

    public static void writeVarInt(int value, ByteBuf byteBuf) {
        while (true) {
            if ((value & ~SEGMENT_BITS) == 0) {
                byteBuf.writeByte(value);
                return;
            }

            byteBuf.writeByte((value & SEGMENT_BITS) | CONTINUE_BIT);

            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
        }
    }
    public static void writeVarLong(long value, ByteBuf byteBuf) {
        while (true) {
            if ((value & ~((long) SEGMENT_BITS)) == 0) {
                byteBuf.writeByte((int) value);
                return;
            }

            byteBuf.writeByte((int) ((value & SEGMENT_BITS) | CONTINUE_BIT));

            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
        }
    }

    public static UUID readUUID(ByteBuf byteBuf){
        return new UUID(byteBuf.readLong(), byteBuf.readLong());
    }
    public static void writeUUID(UUID uuid, ByteBuf byteBuf) {
        byteBuf.writeLong(uuid.getMostSignificantBits());
        byteBuf.writeLong(uuid.getLeastSignificantBits());
    }
}
