package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class AnimatePacket extends Packet {
    private static final int PACKET_ID = PacketType.ANIMATE_PACKET.getId();
    private final int id;
    private final EntityAnimation animation;

    public AnimatePacket(int id, EntityAnimation animation) {
        this.id = id;
        this.animation = animation;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
        byteBuf.writeByte(animation.getId());
    }

    @Override
    public String toString() {
        return "AnimatePacket{" +
                "id=" + id +
                ", animation=" + animation +
                '}';
    }
}
