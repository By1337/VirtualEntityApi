package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualWitherSkeletonImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualWitherSkeleton {

    public VirtualWitherSkeletonImpl() {
        super(VirtualEntityType.WITHER_SKELETON);
    }
}
