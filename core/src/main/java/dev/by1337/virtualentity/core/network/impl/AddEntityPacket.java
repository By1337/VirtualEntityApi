package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;
import org.by1337.blib.util.Version;

public class AddEntityPacket extends Packet {
    private static final int PACKET_ID = PacketType.ADD_ENTITY_PACKET.getId();
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
        byteBuf.writeByte(virtualEntity.pitch());
        byteBuf.writeByte(virtualEntity.yaw());
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            byteBuf.writeByte(virtualEntity.yaw()); // хз здесь должен быть yHeadRot
            ByteBufUtil.writeVarInt(virtualEntity.getCustomEntityData(), byteBuf);
        } else {
            byteBuf.writeInt(virtualEntity.getCustomEntityData());
        }
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
    }

    @Override
    public String toString() {
        return "AddEntityPacket{" + virtualEntity.getType() + "}";
    }
}
