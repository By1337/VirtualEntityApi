package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownPotion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;

@Deprecated
@RemovedInMinecraftVersion("1.21.5")
public class VirtualThrownPotionImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownPotion {
    private static final ItemStack DEFAULT_ITEM;

    public VirtualThrownPotionImpl() {
        super(VirtualEntityType.POTION);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT_ITEM.clone();
    }

    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_6)) {
            DEFAULT_ITEM = new ItemStack(Material.POTION);
        } else {
            DEFAULT_ITEM = new ItemStack(Material.AIR);
        }
    }
}
