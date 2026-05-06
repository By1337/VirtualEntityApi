package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;

public class DamageEventPacket extends Packet {
    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:damage_event");
    private final int id;


    public DamageEventPacket(int id) {
        this.id = id;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
        ByteBufUtil.writeVarInt(0, byteBuf); //damage type
        ByteBufUtil.writeVarInt(-1, byteBuf);
        ByteBufUtil.writeVarInt(-1, byteBuf);
        byteBuf.writeBoolean(false);
    }

    @Override
    public String toString() {
        return "DamageEventPacket{" +
                "id=" + id +
                '}';
    }
}
