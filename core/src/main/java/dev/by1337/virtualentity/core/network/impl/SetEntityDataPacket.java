package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.syncher.EntityDataSerializers;
import dev.by1337.virtualentity.core.syncher.SynchedEntityData;
import io.netty.buffer.ByteBuf;

import java.util.List;

public class SetEntityDataPacket extends Packet {
    private static final int PACKET_ID = PacketType.SET_ENTITY_DATA_PACKET.getId();
    private final int id;
    private final List<SynchedEntityData.DataItem<?>> dataItems;

    public SetEntityDataPacket(int id, List<SynchedEntityData.DataItem<?>> dataItems) {
        this.id = id;
        this.dataItems = dataItems;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);

        for (SynchedEntityData.DataItem<?> dataItem : dataItems) {
            writeDataItem(byteBuf, dataItem);
        }
        byteBuf.writeByte(255);
    }

    private static <T> void writeDataItem(ByteBuf byteBuf, SynchedEntityData.DataItem<T> dataItem) {
        EntityDataAccessor<T> accessor = dataItem.getAccessor();
        int serializerId = EntityDataSerializers.getId(dataItem.getAccessor().serializer());
        byteBuf.writeByte(accessor.id());
        ByteBufUtil.writeVarInt(serializerId, byteBuf);
        accessor.serializer().write(dataItem.getValue(), byteBuf);
    }

    @Override
    public String toString() {
        return "SetEntityDataPacket{" + "id=" + id +
                ", dataItems=" + dataItems +
                '}';
    }
}
