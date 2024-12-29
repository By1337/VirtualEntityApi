package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualThrownExperienceBottle extends VirtualThrowableItemProjectile {

    static VirtualThrownExperienceBottle create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.EXPERIENCE_BOTTLE, VirtualThrownExperienceBottle.class);
    }
}
