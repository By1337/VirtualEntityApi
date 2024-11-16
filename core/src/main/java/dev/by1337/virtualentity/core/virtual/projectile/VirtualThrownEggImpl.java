package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualThrownEggImpl extends VirtualThrowableItemProjectileImpl implements VirtualThrownEgg {

    public VirtualThrownEggImpl() {
        super(VirtualEntityType.EGG);
    }
}
