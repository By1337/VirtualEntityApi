package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualPillager;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualPillagerImpl extends VirtualRaiderImpl implements VirtualPillager {
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW;

    public VirtualPillagerImpl() {
        super(VirtualEntityType.PILLAGER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_CHARGING_CROSSBOW, false);
    }

    @Override
    public void setChargingCrossbow(boolean flag) {
        this.entityData.set(IS_CHARGING_CROSSBOW, flag);
    }
    @Override
    public boolean isChargingCrossbow(){
        return entityData.get(IS_CHARGING_CROSSBOW);
    }

    static {
        IS_CHARGING_CROSSBOW = Mappings.findAccessor("Pillager", "IS_CHARGING_CROSSBOW");
    }
}