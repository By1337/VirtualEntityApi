package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualStriderImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualStrider {
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_SUFFOCATING;

    public VirtualStriderImpl() {
        super(VirtualEntityType.STRIDER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_BOOST_TIME, 0);
        entityData.define(DATA_SUFFOCATING, false);
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
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public boolean hasSaddle() {
        return false;
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setSaddle(boolean saddle) {
        return;
    }

    static {
        DATA_BOOST_TIME = Mappings.findAccessor("Strider", "DATA_BOOST_TIME");
        DATA_SUFFOCATING = Mappings.findAccessor("Strider", "DATA_SUFFOCATING");

    }
}
