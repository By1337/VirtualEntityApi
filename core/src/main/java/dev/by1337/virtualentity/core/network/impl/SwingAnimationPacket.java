package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;

public class SwingAnimationPacket extends Packet {
    public static final int MAIN_HAND = 0;
    public static final int OFF_HAND = 1;

    private static final int SWING_ANIMATION_TYPE_WHACK = 1;
    private static final int DEFAULT_DURATION = 6;

    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:swing_animation");
    private final int id;
    private final int hand;

    public SwingAnimationPacket(int id, int hand) {
        this.id = id;
        this.hand = hand;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
        ByteBufUtil.writeVarInt(hand, byteBuf);
        ByteBufUtil.writeVarInt(SWING_ANIMATION_TYPE_WHACK, byteBuf);
        ByteBufUtil.writeVarInt(DEFAULT_DURATION, byteBuf);
    }

    @Override
    public String toString() {
        return "SwingAnimationPacket{" +
                "id=" + id +
                ", hand=" + hand +
                '}';
    }
}
