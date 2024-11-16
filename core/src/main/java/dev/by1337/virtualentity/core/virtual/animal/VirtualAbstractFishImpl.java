package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public abstract class VirtualAbstractFishImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualAbstractFish {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET;

    protected VirtualAbstractFishImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public boolean isFromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean flag) {
        this.entityData.set(FROM_BUCKET, flag);
    }

    static {
        FROM_BUCKET = Mappings.findAccessor("AbstractFish", "FROM_BUCKET");
    }
}