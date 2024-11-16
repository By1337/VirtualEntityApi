package dev.by1337.virtualentity.api.virtual.item;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3d;

public interface VirtualItem extends VirtualEntity {
    void setNoMotion();

    void setMotion(Vec3d motion);

    ItemStack getItem();

    void setItem(ItemStack item);
}
