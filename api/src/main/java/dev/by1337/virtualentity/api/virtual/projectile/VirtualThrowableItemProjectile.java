package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;

public interface VirtualThrowableItemProjectile extends VirtualEntity {
    ItemStack getItemStack();
    void setItemStack(ItemStack itemStack);
}
