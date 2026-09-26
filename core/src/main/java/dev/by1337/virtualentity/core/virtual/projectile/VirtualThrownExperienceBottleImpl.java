package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VirtualThrownExperienceBottleImpl extends VirtualThrowableItemProjectileImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownExperienceBottle {

    private static final ItemStack DEFAULT_ITEM;

    public VirtualThrownExperienceBottleImpl() {
        super(VirtualEntityType.EXPERIENCE_BOTTLE);
    }

    @Override
    protected ItemStack getDefaultItem() {
        return DEFAULT_ITEM.clone();
    }

    static {
        DEFAULT_ITEM = new ItemStack(Material.EXPERIENCE_BOTTLE);
    }
}
