package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualThrownEnderpearl extends VirtualThrowableItemProjectile {

    static VirtualThrownEnderpearl create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ENDER_PEARL, VirtualThrownEnderpearl.class);
    }
}
