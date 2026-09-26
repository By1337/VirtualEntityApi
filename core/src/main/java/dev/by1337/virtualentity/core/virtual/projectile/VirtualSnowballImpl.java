package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualSnowballImpl extends VirtualThrowableItemProjectileImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualSnowball {
    private static final ItemStack DEFAULT_ITEM;

    public VirtualSnowballImpl() {
        super(VirtualEntityType.SNOWBALL);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT_ITEM.clone();
    }

    static {
        DEFAULT_ITEM = new ItemStack(Material.SNOWBALL);
    }
}
