package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualDragonFireball extends VirtualEntity {

    static VirtualDragonFireball create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.DRAGON_FIREBALL, VirtualDragonFireball.class);
    }
}
