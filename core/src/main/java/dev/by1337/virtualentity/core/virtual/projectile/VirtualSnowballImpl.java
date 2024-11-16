package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualSnowballImpl extends VirtualThrowableItemProjectileImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualSnowball {

    public VirtualSnowballImpl() {
        super(VirtualEntityType.SNOWBALL);
    }
}
