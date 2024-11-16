package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualRaider;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public abstract class VirtualRaiderImpl extends VirtualMobImpl implements VirtualRaider {
    private static final EntityDataAccessor<Boolean> IS_CELEBRATING;

    protected VirtualRaiderImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_CELEBRATING, false);
    }

    @Override
    public void setCelebrating(boolean flag) {
        this.entityData.set(IS_CELEBRATING, flag);
    }

    @Override
    public boolean isCelebrating() {
        return entityData.get(IS_CELEBRATING);
    }

    static {
        IS_CELEBRATING = Mappings.findAccessor("Raider", "IS_CELEBRATING");
    }
}