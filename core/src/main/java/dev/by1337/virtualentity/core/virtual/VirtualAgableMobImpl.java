package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public abstract class VirtualAgableMobImpl extends VirtualMobImpl implements VirtualAgableMob {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;

    public VirtualAgableMobImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
    }

    @Override
    public boolean isBaby() {
        return !this.entityData.get(DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean flag) {
        this.entityData.set(DATA_BABY_ID, flag);
    }

    static {
        DATA_BABY_ID = Mappings.findAccessor("AgableMob", "DATA_BABY_ID");
    }
}
