package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualThrownEgg extends dev.by1337.virtualentity.api.virtual.projectile.VirtualThrowableItemProjectile {

    static VirtualThrownEgg create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.EGG, VirtualThrownEgg.class);
    }
}
