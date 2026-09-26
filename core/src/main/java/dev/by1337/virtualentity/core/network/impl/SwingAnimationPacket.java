package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;

/** The default arm swing, moved out of minecraft:animate in protocol 777. */
public class SwingAnimationPacket extends Packet {
    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:swing_animation");
    private final int id;
    private final boolean offHand;

    public SwingAnimationPacket(int id, boolean offHand) {
        this.id = id;
        this.offHand = offHand;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        if (ServerVersion.CURRENT_PROTOCOL != 777) {
            throw new UnsupportedOperationException("SwingAnimationPacket requires protocol 777");
        }
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);
        ByteBufUtil.writeVarInt(offHand ? 1 : 0, byteBuf);
        ByteBufUtil.writeVarInt(1, byteBuf); // SwingAnimationType.WHACK
        ByteBufUtil.writeVarInt(6, byteBuf); // SwingAnimation.DEFAULT.duration
    }
}
