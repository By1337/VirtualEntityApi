package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

public class AddPaintingPacket extends Packet {
    private static final int PACKET_ID = Mappings.getPacketId(PacketType.ADD_PAINTING_PACKET);
    private final VirtualPainting painting;

    public AddPaintingPacket(VirtualPainting painting) {
        this.painting = painting;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBuffUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBuffUtil.writeVarInt(painting.getId(), byteBuf);
        ByteBuffUtil.writeUUID(painting.getUuid(), byteBuf);
    }
}
