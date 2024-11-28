package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;
import org.bukkit.inventory.ItemStack;

public interface VirtualItemFrame extends VirtualHangingEntity {
    ItemStack getItem();

    void setItem(ItemStack param0);

    int getRotation();

    void setRotation(int rotate);
}
