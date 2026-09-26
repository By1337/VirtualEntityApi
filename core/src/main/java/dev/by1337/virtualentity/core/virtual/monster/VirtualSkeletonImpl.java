package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualSkeletonImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualSkeleton {

    private static final EntityDataAccessor<Boolean> DATA_STRAY_CONVERSION_ID;

    public VirtualSkeletonImpl() {
        super(VirtualEntityType.SKELETON);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_STRAY_CONVERSION_ID, false);
    }

    @Override
    public boolean isStrayConversion() {
        return entityData.get(DATA_STRAY_CONVERSION_ID);
    }

    @Override
    public void setStrayConversion(boolean flag) {
        entityData.set(DATA_STRAY_CONVERSION_ID, flag);
    }

    static {
        DATA_STRAY_CONVERSION_ID = Mappings.findAccessor("Skeleton", "DATA_STRAY_CONVERSION_ID");
    }
}
