package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import dev.by1337.virtualentity.core.virtual.decoration.VirtualArmorStandImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.by1337.blib.geom.Vec3d;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TeleportEntityPacketTest {


    @Test
    public void run() {
        SetupServer.setup();
        System.setProperty("blib.server.version", "1.16.5");
        VirtualArmorStandImpl armorStand = new VirtualArmorStandImpl();
        Random random = new Random();
        armorStand.setPos(new Vec3d(random.nextInt(), random.nextInt(), random.nextInt()));
        armorStand.setYaw(random.nextFloat() * 10);
        armorStand.setPitch(random.nextFloat() * 10);

        TeleportEntityPacket teleportEntityPacket = new TeleportEntityPacket(armorStand);

        ByteBuf byteBuf = Unpooled.buffer();
        teleportEntityPacket.write(byteBuf);

        ByteBuffUtil.readVarInt(byteBuf); // skip packet id

        assertEquals(armorStand.getId(), ByteBuffUtil.readVarInt(byteBuf)); // Entity ID
        assertEquals(armorStand.getPos().x, byteBuf.readDouble(), 0);
        assertEquals(armorStand.getPos().y, byteBuf.readDouble(), 0);
        assertEquals(armorStand.getPos().z, byteBuf.readDouble(), 0);
        assertEquals(armorStand.yaw(), byteBuf.readByte());
        assertEquals(armorStand.pitch(), byteBuf.readByte());
        assertEquals(armorStand.isOnGround(), byteBuf.readBoolean());
        assertEquals(0, byteBuf.readableBytes());

    }
}