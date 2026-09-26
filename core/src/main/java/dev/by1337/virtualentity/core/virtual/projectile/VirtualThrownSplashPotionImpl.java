package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownPotion;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownSplashPotion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualThrownSplashPotionImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownSplashPotion, VirtualThrownPotion {
    private static final ItemStack DEFAULT = new ItemStack(Material.SPLASH_POTION);

    public VirtualThrownSplashPotionImpl() {
        super(VirtualEntityType.SPLASH_POTION);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT.clone();
    }

}
