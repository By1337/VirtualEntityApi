package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;

public interface VirtualItemFrame extends VirtualEntity {
    ItemStack getItem();

    void setItem(ItemStack param0);

    int getRotation();

    void setRotation(int rotate);
}
