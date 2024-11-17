package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

public abstract class VirtualAgeableMobImpl extends VirtualMobImpl implements VirtualAgeableMob {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;

    public VirtualAgeableMobImpl(VirtualEntityType type) {
        super(type); // AgableMob Ageable
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
    }

    @Override
    public boolean isBaby() {
        return !this.entityData.get(DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean flag) {
        this.entityData.set(DATA_BABY_ID, flag);
    }

    static {
        if (Version.VERSION.olderThan(Version.V1_17_1))
            DATA_BABY_ID = Mappings.findAccessor("AgableMob", "DATA_BABY_ID");
        else
            DATA_BABY_ID = Mappings.findAccessor("AgeableMob", "DATA_BABY_ID");
    }
}
