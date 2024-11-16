package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

public class VirtualPigImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualPig {
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;

    public VirtualPigImpl() {
        super(VirtualEntityType.PIG);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SADDLE_ID, false);
        this.entityData.define(DATA_BOOST_TIME, 0);
    }

    @Override
    public int getBoostTime() {
        return entityData.get(DATA_BOOST_TIME);
    }

    @Override
    public void setBoostTime(int boostTime) {
        entityData.set(DATA_BOOST_TIME, boostTime);
    }

    @Override
    public boolean isSaddle() {
        return entityData.get(DATA_SADDLE_ID);
    }

    @Override
    public void setSaddle(boolean flag) {
        entityData.set(DATA_SADDLE_ID, flag);
    }

    static {
        DATA_SADDLE_ID = Mappings.findAccessor("Pig", "DATA_SADDLE_ID");
        DATA_BOOST_TIME = Mappings.findAccessor("Pig", "DATA_BOOST_TIME");
    }
}
