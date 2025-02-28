package dev.by1337.virtualentity.api.virtual.item;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;

public interface VirtualItem extends VirtualEntity {

    ItemStack getItem();

    void setItem(ItemStack item);

    static VirtualItem create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ITEM, VirtualItem.class);
    }
}
