package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualPufferfishImpl extends VirtualAbstractFishImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualPufferfish {
    private static final EntityDataAccessor<Integer> PUFF_STATE;

    public VirtualPufferfishImpl() {
        super(VirtualEntityType.PUFFERFISH);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PUFF_STATE, 0);
    }

    @Override
    public int getPuffState() {
        return this.entityData.get(PUFF_STATE);
    }

    // 0 1 2
    @Override
    public void setPuffState(int state) {
        this.entityData.set(PUFF_STATE, state);
    }

    static {
        PUFF_STATE = Mappings.findAccessor("Pufferfish", "PUFF_STATE");
    }
}
