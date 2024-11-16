package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class AnimatePacket extends Packet {
    private final int id;
    private final EntityAnimation animation;

    public AnimatePacket(int id, EntityAnimation animation) {
        this.id = id;
        this.animation = animation;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        // https://wiki.vg/index.php?title=Protocol&oldid=16681#Entity_Animation_.28clientbound.29
        ByteBuffCodecs.VAR_INT.accept(Mappings.getPacketId(PacketType.ANIMATE_PACKET), byteBuf);
        ByteBuffCodecs.VAR_INT.accept(id, byteBuf);
        byteBuf.writeByte(animation.ordinal());
    }
}
