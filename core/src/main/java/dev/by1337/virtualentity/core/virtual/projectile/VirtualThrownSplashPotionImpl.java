package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownSplashPotion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SinceMinecraftVersion("1.21.5")
public class VirtualThrownSplashPotionImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownSplashPotion {
    private static final ItemStack DEFAULT = new ItemStack(Material.SPLASH_POTION);

    public VirtualThrownSplashPotionImpl() {
        super(VirtualEntityType.SPLASH_POTION);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT.clone();
    }

}