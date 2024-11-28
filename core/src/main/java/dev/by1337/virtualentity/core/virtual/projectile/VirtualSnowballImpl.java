package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;

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
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_6)) {
            DEFAULT_ITEM = new ItemStack(Material.SNOWBALL);
        } else {
            DEFAULT_ITEM = new ItemStack(Material.AIR);
        }
    }
}
