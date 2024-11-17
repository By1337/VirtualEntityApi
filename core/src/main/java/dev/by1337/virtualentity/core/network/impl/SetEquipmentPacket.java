package dev.by1337.virtualentity.core.network.impl;

import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffUtil;
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
        ByteBuffUtil.writeVarInt(PACKET_ID, byteBuf);
        ByteBuffUtil.writeVarInt(id, byteBuf);

        var iterator = items.entrySet().iterator();

        while (iterator.hasNext()) {
            var entry = iterator.next();
            EquipmentSlot slot = entry.getKey();
            ItemStack itemStack = entry.getValue();

            int slotId = slot.getId();

            byteBuf.writeByte(iterator.hasNext() ? slotId | -128 : slotId);
            ByteBuffUtil.writeItemStack(itemStack, byteBuf);
        }

    }
}
