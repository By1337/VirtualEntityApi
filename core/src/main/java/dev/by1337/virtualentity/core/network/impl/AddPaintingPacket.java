package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting;
import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

@RemovedInMinecraftVersion("1.19.4")
public class AddPaintingPacket extends Packet {
    private static final int PACKET_ID = PacketType.ADD_PAINTING_PACKET.getId();
    private final VirtualPainting painting;

    public AddPaintingPacket(VirtualPainting painting) {
        this.painting = painting;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        //https://wiki.vg/index.php?title=Protocol&oldid=16866#Spawn_Painting
        ByteBuffUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBuffUtil.writeVarInt(painting.getId(), byteBuf);
        ByteBuffUtil.writeUUID(painting.getUuid(), byteBuf);
        ByteBuffUtil.writeVarInt(painting.motive().getId(), byteBuf);
        ByteBuffUtil.writeBlockPos(painting.getPos().toBlockPos(), byteBuf);
        byteBuf.writeByte(
                switch (painting.direction()) {
                    case DOWN, UP -> -1;
                    case NORTH -> 2;
                    case SOUTH -> 0;
                    case WEST -> 1;
                    case EAST -> 3;
                }
        );
    }
}
