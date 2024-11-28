package dev.by1337.virtualentity.api.virtual;

import org.bukkit.inventory.ItemStack;

public interface VirtualOminousItemSpawner extends VirtualEntity {
    void setItem(ItemStack item);

    ItemStack getItem();
}
