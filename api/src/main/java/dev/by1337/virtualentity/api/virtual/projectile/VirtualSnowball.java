package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualSnowball extends VirtualThrowableItemProjectile {
    static VirtualSnowball create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SNOWBALL, VirtualSnowball.class);
    }
}
