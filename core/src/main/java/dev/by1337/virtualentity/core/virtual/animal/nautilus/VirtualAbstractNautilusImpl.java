package dev.by1337.virtualentity.core.virtual.animal.nautilus;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.animal.VirtualTamableAnimalImpl;

public class VirtualAbstractNautilusImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.nautilus.VirtualAbstractNautilus {
    private static final EntityDataAccessor<Boolean> DASH;

    public VirtualAbstractNautilusImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DASH, false);
    }

    public boolean isDashing() {
        return this.entityData.get(DASH);
    }

    public void setDashing(boolean dashing) {
        this.entityData.set(DASH, dashing);
    }

    static {
        DASH = Mappings.findAccessor("AbstractNautilus", "DASH");
    }
}
