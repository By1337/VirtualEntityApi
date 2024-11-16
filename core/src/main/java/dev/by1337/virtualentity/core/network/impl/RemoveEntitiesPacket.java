package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class RemoveEntitiesPacket extends Packet {
    private final int id;

    public RemoveEntitiesPacket(int id) {
        this.id = id;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBuffCodecs.VAR_INT.accept(Mappings.getPacketId(PacketType.REMOVE_ENTITIES_PACKET), byteBuf);
        ByteBuffCodecs.VAR_INT.accept(1, byteBuf);
        ByteBuffCodecs.VAR_INT.accept(id, byteBuf);
    }
}
