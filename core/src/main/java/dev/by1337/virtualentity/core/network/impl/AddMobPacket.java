package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;

@RemovedInMinecraftVersion("1.19.4")
public class AddMobPacket extends Packet {
    private static final int PACKET_ID = PacketType.ADD_MOB_PACKET.getId();
    private final VirtualEntity virtualEntity;

    public AddMobPacket(VirtualEntity virtualEntity) {
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
        byteBuf.writeByte(virtualEntity.yaw());
        byteBuf.writeByte(virtualEntity.pitch());
        byteBuf.writeByte(virtualEntity.yaw()); // хз здесь должен быть yHeadRot
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
    }
    @Override
    public String toString() {
        return "AddMobPacket{" + virtualEntity.getType() + "}";
    }
}
