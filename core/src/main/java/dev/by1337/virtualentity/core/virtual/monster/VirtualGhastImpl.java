package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualGhastImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualGhast {
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING;

    public VirtualGhastImpl() {
        super(VirtualEntityType.GHAST);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }

    @Override
    public void setCharging(boolean param0) {
        this.entityData.set(DATA_IS_CHARGING, param0);
    }

    @Override
    public boolean isCharging() {
        return entityData.get(DATA_IS_CHARGING);
    }

    static {
        DATA_IS_CHARGING = Mappings.findAccessor("Ghast", "DATA_IS_CHARGING");
    }
}
