package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualSkeletonImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualSkeleton {

    public VirtualSkeletonImpl() {
        super(VirtualEntityType.SKELETON);
    }
}
