package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

public class RemoveEntitiesPacket extends Packet {
    private static final int PACKET_ID_755 = Packets.play.clientbound.getId("minecraft:remove_entity");
    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:remove_entities");
    private final int id;

    public RemoveEntitiesPacket(int id) {
        this.id = id;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        if (ServerVersion.CURRENT_PROTOCOL == 755) {
            ByteBufUtil.writeVarInt(PACKET_ID_755, byteBuf);
            ByteBufUtil.writeVarInt(id, byteBuf);
        } else {
            ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
            ByteBufUtil.writeVarInt(1, byteBuf);
            ByteBufUtil.writeVarInt(id, byteBuf);
        }
    }

    @Override
    public String toString() {
        return "RemoveEntitiesPacket{" +
                "ids=" + id +
                '}';
    }
}
