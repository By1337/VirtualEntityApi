package dev.by1337.virtualentity.core.virtual;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public abstract class VirtualAgeableMobImpl extends VirtualMobImpl implements VirtualAgeableMob {

    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;
    @SinceMinecraftVersion("26.1")
    private static final EntityDataAccessor<Boolean> AGE_LOCKED;
    public VirtualAgeableMobImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (!hasAgeableData()) return;
        this.entityData.define(DATA_BABY_ID, false);
        if (ServerVersion.is26_1orNewer()){
            this.entityData.define(AGE_LOCKED, false);
        }
    }

    protected boolean hasAgeableData() {
        return true;
    }
    @SinceMinecraftVersion("26.1")
    public boolean isAgeLocked() {
        return entityData.get(AGE_LOCKED);
    }
    @SinceMinecraftVersion("26.1")
    public void setAgeLocked(boolean value) {
        entityData.set(AGE_LOCKED, value);
    }

    @Override
    public boolean isBaby() {
        return hasAgeableData() && this.entityData.get(DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean flag) {
        if (hasAgeableData()) this.entityData.set(DATA_BABY_ID, flag);
    }

    static {

        DATA_BABY_ID = Mappings.findAccessor("AgeableMob", "DATA_BABY_ID");

        if (ServerVersion.is26_1orNewer()){
            AGE_LOCKED = Mappings.findAccessor("AgeableMob", "AGE_LOCKED");
        }else{
            AGE_LOCKED = null;
        }
    }
}
