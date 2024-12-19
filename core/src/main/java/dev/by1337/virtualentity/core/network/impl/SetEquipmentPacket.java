package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import io.netty.buffer.ByteBuf;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class SetEquipmentPacket extends Packet {
    private static final int PACKET_ID = PacketType.SET_EQUIPMENT_PACKET.getId();
    private final int id;
    private final Map<EquipmentSlot, ItemStack> items;

    public SetEquipmentPacket(int id, Map<EquipmentSlot, ItemStack> items) {
        this.id = id;
        this.items = items;
    }

    @Override
    public void write(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBufUtil.writeVarInt(id, byteBuf);

        var iterator = items.entrySet().iterator();

        while (iterator.hasNext()) {
            var entry = iterator.next();
            EquipmentSlot slot = entry.getKey();
            ItemStack itemStack = entry.getValue();

            int slotId = slot.getId();

            byteBuf.writeByte(iterator.hasNext() ? slotId | -128 : slotId);
            ByteBufUtil.writeItemStack(itemStack, byteBuf);
        }
    }

    @Override
    public String toString() {
        return "SetEquipmentPacket{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }
}
