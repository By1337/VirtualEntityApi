package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.entity.EntityEvent;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class EntityEventPacket extends Packet {
    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:entity_event");
    private final int id;
    private final int event;

    public EntityEventPacket(int id, int event) {
        this.id = id;
        this.event = event;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        byteBuf.writeInt(id);
        byteBuf.writeByte(event);
    }

    @Override
    public String toString() {
        return "EntityEventPacket{" +
                "id=" + id +
                ", event=" + event +
                '}';
    }
}
