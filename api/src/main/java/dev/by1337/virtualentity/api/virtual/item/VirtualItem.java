package dev.by1337.virtualentity.api.virtual.item;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3d;

public interface VirtualItem extends VirtualEntity {
    void setNoMotion();

    void setMotion(Vec3d motion);

    ItemStack getItem();

    void setItem(ItemStack item);

    static VirtualItem create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ITEM, VirtualItem.class);
    }
}
