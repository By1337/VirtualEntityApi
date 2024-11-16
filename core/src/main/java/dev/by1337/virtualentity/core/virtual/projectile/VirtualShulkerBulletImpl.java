package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.VirtualShulkerBullet;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualShulkerBulletImpl extends VirtualEntityImpl implements VirtualShulkerBullet {

    public VirtualShulkerBulletImpl() {
        super(VirtualEntityType.SHULKER_BULLET);
    }
}
