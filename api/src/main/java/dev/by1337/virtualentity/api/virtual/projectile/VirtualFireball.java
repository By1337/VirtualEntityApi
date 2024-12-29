package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.inventory.ItemStack;

public interface VirtualFireball extends VirtualEntity {
    void setItem(ItemStack itemStack);

    ItemStack getItem();
    static VirtualFireball create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SMALL_FIREBALL, VirtualFireball.class);
    }
}
