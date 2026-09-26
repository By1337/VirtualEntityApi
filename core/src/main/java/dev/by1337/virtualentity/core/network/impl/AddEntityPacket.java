package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import io.netty.buffer.ByteBuf;

public class AddEntityPacket extends Packet {
    private static final int PACKET_ID = Packets.play.clientbound.getId("minecraft:add_entity");
    private final VirtualEntity virtualEntity;

    public AddEntityPacket(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(virtualEntity.getId(), byteBuf);
        ByteBufUtil.writeUUID(virtualEntity.getUuid(), byteBuf);
        ByteBufUtil.writeVarInt(Mappings.getNetworkId(virtualEntity.getType()), byteBuf);
        byteBuf.writeDouble(virtualEntity.getPos().x);
        byteBuf.writeDouble(virtualEntity.getPos().y);
        byteBuf.writeDouble(virtualEntity.getPos().z);
        byteBuf.writeByte(0); // LpVec3 (velocity)
        byteBuf.writeByte(virtualEntity.pitch());
        byteBuf.writeByte(virtualEntity.yaw());
        byteBuf.writeByte(virtualEntity.yaw()); // хз здесь должен быть yHeadRot
        ByteBufUtil.writeVarInt(virtualEntity.getCustomEntityData(), byteBuf);

    }

    @Override
    public String toString() {
        return "AddEntityPacket{" + virtualEntity.getType() + "}";
    }
}
