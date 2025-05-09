package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownLingeringPotion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualThrownLingeringPotionImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownLingeringPotion {
    private static final ItemStack DEFAULT = new ItemStack(Material.LINGERING_POTION);

    public VirtualThrownLingeringPotionImpl() {
        super(VirtualEntityType.SPLASH_POTION);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT.clone();
    }

}