package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualZoglinImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualZoglin {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;

    public VirtualZoglinImpl() {
        super(VirtualEntityType.ZOGLIN);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
    }

    @Override
    public void setBaby(boolean param0) {
        this.entityData.set(DATA_BABY_ID, param0);
    }

    @Override
    public boolean isBaby() {
        return this.entityData.get(DATA_BABY_ID);
    }

    static {
        DATA_BABY_ID = Mappings.findAccessor("Zoglin", "DATA_BABY_ID");
    }
}
