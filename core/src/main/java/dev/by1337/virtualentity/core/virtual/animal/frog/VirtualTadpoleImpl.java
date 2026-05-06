package dev.by1337.virtualentity.core.virtual.animal.frog;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.animal.VirtualAbstractFishImpl;

@SinceMinecraftVersion("1.19.4")
public class VirtualTadpoleImpl extends VirtualAbstractFishImpl implements dev.by1337.virtualentity.api.virtual.animal.frog.VirtualTadpole {
    @SinceMinecraftVersion("26.1")
    private static final EntityDataAccessor<Boolean> AGE_LOCKED;

    public VirtualTadpoleImpl() {
        super(VirtualEntityType.TADPOLE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (ServerVersion.is26_1orNewer()){
            entityData.define(AGE_LOCKED, false);
        }
    }
    @SinceMinecraftVersion("26.1")
    @Override
    public boolean isAgeLocked() {
        return entityData.get(AGE_LOCKED);
    }
    @SinceMinecraftVersion("26.1")
    @Override
    public void setAgeLocked(boolean value) {
        entityData.set(AGE_LOCKED, value);
    }

    static {
        if (ServerVersion.is26_1orNewer()){
            AGE_LOCKED = Mappings.findAccessor("Tadpole", "AGE_LOCKED");
        }else {
            AGE_LOCKED = null;
        }
    }
}