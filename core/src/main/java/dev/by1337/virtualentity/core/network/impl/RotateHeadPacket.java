package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class RotateHeadPacket extends Packet {
    private static final int PACKET_ID = PacketType.ROTATE_HEAD_PACKET.getId();
    private final int id;
    private final byte yHeadRot;

    public RotateHeadPacket(int id, byte yHeadRot) {
        this.id = id;
        this.yHeadRot = yHeadRot;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
        byteBuf.writeByte(yHeadRot);
    }

    @Override
    public String toString() {
        return "RotateHeadPacket{" +
                "id=" + id +
                ", yHeadRot=" + yHeadRot +
                '}';
    }
}
