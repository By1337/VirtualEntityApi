package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.entity.EntityEvent;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class EntityEventPacket extends Packet {
    private static final int PACKET_ID = PacketType.ENTITY_EVENT_PACKET.getId();
    private final int id;
    private final EntityEvent event;

    public EntityEventPacket(int id, EntityEvent event) {
        this.id = id;
        this.event = event;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        byteBuf.writeInt(id);
        byteBuf.writeByte(event.getId());
    }

    @Override
    public String toString() {
        return "EntityEventPacket{" +
                "id=" + id +
                ", event=" + event +
                '}';
    }
}
