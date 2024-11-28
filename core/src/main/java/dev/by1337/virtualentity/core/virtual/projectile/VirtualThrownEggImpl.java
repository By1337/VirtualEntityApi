package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownEgg;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;

public class VirtualThrownEggImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownEgg {
    private static final ItemStack DEFAULT_ITEM;
    public VirtualThrownEggImpl() {
        super(VirtualEntityType.EGG);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT_ITEM.clone();
    }
    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_6)) {
            DEFAULT_ITEM = new ItemStack(Material.EGG);
        } else {
            DEFAULT_ITEM = new ItemStack(Material.AIR);
        }
    }
}
