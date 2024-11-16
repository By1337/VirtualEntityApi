package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

public class VirtualPolarBearImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualPolarBear {
    private static final EntityDataAccessor<Boolean> DATA_STANDING_ID;

    public VirtualPolarBearImpl() {
        super(VirtualEntityType.POLAR_BEAR);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STANDING_ID, false);
    }

    @Override
    public boolean isStanding() {
        return this.entityData.get(DATA_STANDING_ID);
    }

    @Override
    public void setStanding(boolean flag) {
        this.entityData.set(DATA_STANDING_ID, flag);
    }

    static {
        DATA_STANDING_ID = Mappings.findAccessor("PolarBear", "DATA_STANDING_ID");
    }
}
