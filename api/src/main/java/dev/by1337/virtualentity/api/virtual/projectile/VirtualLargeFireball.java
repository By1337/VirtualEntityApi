package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualLargeFireball extends VirtualFireball {
    static VirtualLargeFireball create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.FIREBALL, VirtualLargeFireball.class);
    }
}
