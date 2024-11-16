package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualThrownPotionImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownPotion {

    public VirtualThrownPotionImpl() {
        super(VirtualEntityType.POTION);
    }
}
