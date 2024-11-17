package dev.by1337.virtualentity.core.network.impl;


import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import dev.by1337.virtualentity.core.virtual.decoration.VirtualArmorStandImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.by1337.blib.geom.Vec3d;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AddEntityPacketTest {


    @Test
    public void run() {
        SetupServer.setup();
        System.setProperty("blib.server.version", "1.16.5");
        VirtualArmorStandImpl armorStand = new VirtualArmorStandImpl();

        Random random = new Random();
        armorStand.setPos(new Vec3d(random.nextInt(), random.nextInt(), random.nextInt()));
        armorStand.setYaw(random.nextFloat() * 10);
        armorStand.setPitch(random.nextFloat() * 10);

        AddEntityPacket addEntityPacket = new AddEntityPacket(armorStand);

        ByteBuf byteBuf = Unpooled.buffer();
        addEntityPacket.write(byteBuf);

        ByteBuffUtil.readVarInt(byteBuf); // skip packet id

        assertEquals(armorStand.getId(), ByteBuffUtil.readVarInt(byteBuf)); // Entity ID
        assertEquals(armorStand.getUuid(), ByteBuffUtil.readUUID(byteBuf));
        ByteBuffUtil.readVarInt(byteBuf); // entity type
        assertEquals(armorStand.getPos().x, byteBuf.readDouble(), 0);
        assertEquals(armorStand.getPos().y, byteBuf.readDouble(), 0);
        assertEquals(armorStand.getPos().z, byteBuf.readDouble(), 0);
        assertEquals(armorStand.pitch(), byteBuf.readByte());
        assertEquals(armorStand.yaw(), byteBuf.readByte());
        byteBuf.readInt(); // entity data
        // velocity
        byteBuf.readShort();
        byteBuf.readShort();
        byteBuf.readShort();

        assertEquals(0, byteBuf.readableBytes());
    }



//    @Test
//    public void run0() {
//        System.setProperty("blib.server.version", "1.16.5");
//        Mappings mappings = Mappings.instance;
//
//        System.out.println(mappings.enumMappings());
//    }


}