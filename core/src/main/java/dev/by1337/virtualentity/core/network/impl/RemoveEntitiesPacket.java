package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class RemoveEntitiesPacket extends Packet {
    private static final int PACKET_ID = PacketType.REMOVE_ENTITIES_PACKET.getId();
    private final int id;

    public RemoveEntitiesPacket(int id) {
        this.id = id;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(1, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
    }

    @Override
    public String toString() {
        return "RemoveEntitiesPacket{" +
                "id=" + id +
                '}';
    }
}
