package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualOcelotImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualOcelot {
    private static final EntityDataAccessor<Boolean> DATA_TRUSTING;

    public VirtualOcelotImpl() {
        super(VirtualEntityType.OCELOT);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TRUSTING, false);
    }

    @Override
    public boolean isTrusting() {
        return this.entityData.get(DATA_TRUSTING);
    }

    @Override
    public void setTrusting(boolean flag) {
        this.entityData.set(DATA_TRUSTING, flag);
    }

    static {
        DATA_TRUSTING = Mappings.findAccessor("Ocelot", "DATA_TRUSTING");
    }
}