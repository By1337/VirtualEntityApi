package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class RotateHeadPacket extends Packet {
    private static final int PACKET_ID = Mappings.getPacketId(PacketType.ROTATE_HEAD_PACKET);
    private final int id;
    private final byte yHeadRot;

    public RotateHeadPacket(int id, byte yHeadRot) {
        this.id = id;
        this.yHeadRot = yHeadRot;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBuffCodecs.VAR_INT.accept(PACKET_ID, byteBuf);
        ByteBuffCodecs.VAR_INT.accept(id, byteBuf);
        byteBuf.writeByte(yHeadRot);
    }
}
