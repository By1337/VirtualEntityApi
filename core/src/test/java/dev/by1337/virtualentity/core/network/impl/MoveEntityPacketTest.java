package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import dev.by1337.virtualentity.core.virtual.VirtualArmorStandImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MoveEntityPacketTest {


    @Test
    public void run() throws IOException {
        SetupServer.setup();
        VirtualArmorStandImpl armorStand = new VirtualArmorStandImpl();

        MoveEntityPacket.PosRot teleportEntityPacket = new MoveEntityPacket.PosRot(armorStand);

        ByteBuf byteBuf = Unpooled.buffer();
        teleportEntityPacket.write(byteBuf);

        ByteBuffUtil.readVarInt(byteBuf); // skip packet id

        assertEquals(armorStand.getId(), ByteBuffUtil.readVarInt(byteBuf)); // Entity ID
        assertEquals(0, byteBuf.readShort());
        assertEquals(0, byteBuf.readShort());
        assertEquals(0, byteBuf.readShort());

        assertEquals(0, byteBuf.readByte());
        assertEquals(0, byteBuf.readByte());
        assertEquals(true, byteBuf.readBoolean());
        assertEquals(0, byteBuf.readableBytes());

    }
}