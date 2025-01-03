package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.virtual.decoration.VirtualArmorStandImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveEntityPacketTest {


    @Test
    public void run() {
        SetupServer.setup();
        System.setProperty("blib.server.version", "1.16.5");
        VirtualArmorStandImpl armorStand = new VirtualArmorStandImpl();

        MoveEntityPacket.PosRot teleportEntityPacket = new MoveEntityPacket.PosRot(armorStand);

        ByteBuf byteBuf = Unpooled.buffer();
        teleportEntityPacket.write(byteBuf);

        ByteBufUtil.readVarInt(byteBuf); // skip packet id

        assertEquals(armorStand.getId(), ByteBufUtil.readVarInt(byteBuf)); // Entity ID
        assertEquals(0, byteBuf.readShort());
        assertEquals(0, byteBuf.readShort());
        assertEquals(0, byteBuf.readShort());

        assertEquals(0, byteBuf.readByte());
        assertEquals(0, byteBuf.readByte());
        assertEquals(true, byteBuf.readBoolean());
        assertEquals(0, byteBuf.readableBytes());

    }
}