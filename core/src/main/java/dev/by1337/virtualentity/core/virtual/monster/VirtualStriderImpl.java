package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualStriderImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualStrider {
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_SUFFOCATING;
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;

    public VirtualStriderImpl() {
        super(VirtualEntityType.STRIDER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BOOST_TIME, 0);
        this.entityData.define(DATA_SUFFOCATING, false);
        this.entityData.define(DATA_SADDLE_ID, false);
    }

    @Override
    public int getBoostTime() {
        return this.entityData.get(DATA_BOOST_TIME);
    }

    @Override
    public void setBoostTime(int boostTime) {
        this.entityData.set(DATA_BOOST_TIME, boostTime);
    }

    @Override
    public boolean isSuffocating() {
        return this.entityData.get(DATA_SUFFOCATING);
    }

    @Override
    public void setSuffocating(boolean suffocating) {
        this.entityData.set(DATA_SUFFOCATING, suffocating);
    }

    @Override
    public boolean hasSaddle() {
        return this.entityData.get(DATA_SADDLE_ID);
    }

    @Override
    public void setSaddle(boolean saddle) {
        this.entityData.set(DATA_SADDLE_ID, saddle);
    }

    static {
        DATA_BOOST_TIME = Mappings.findAccessor("Strider", "DATA_BOOST_TIME");
        DATA_SUFFOCATING = Mappings.findAccessor("Strider", "DATA_SUFFOCATING");
        DATA_SADDLE_ID = Mappings.findAccessor("Strider", "DATA_SADDLE_ID");
    }
}
