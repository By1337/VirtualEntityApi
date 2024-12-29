package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualShulkerBullet extends VirtualEntity {
    static VirtualShulkerBullet create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SHULKER_BULLET, VirtualShulkerBullet.class);
    }
}
