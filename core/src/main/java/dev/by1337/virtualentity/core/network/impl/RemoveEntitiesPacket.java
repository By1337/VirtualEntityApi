package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

public class RemoveEntitiesPacket extends Packet {
    private static final int PACKET_ID = PacketType.REMOVE_ENTITIES_PACKET.getId();
    private final int[] id;

    public RemoveEntitiesPacket(int id) {
        this.id = new int[]{id};
    }

    public RemoveEntitiesPacket(int... id) {
        this.id = id;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id.length, byteBuf);
        for (int i : id) {
            ByteBufUtil.writeVarInt(i, byteBuf);
        }
    }

    @Override
    public String toString() {
        return "RemoveEntitiesPacket{" +
                "ids=" + Arrays.toString(id) +
                '}';
    }
}
