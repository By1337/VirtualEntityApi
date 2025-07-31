package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

@SinceMinecraftVersion("1.21.6")
public class VirtualHappyGhastImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualHappyGhast {
    private static final EntityDataAccessor<Boolean> IS_LEASH_HOLDER;
    private static final EntityDataAccessor<Boolean> STAYS_STILL;

    public VirtualHappyGhastImpl() {
        super(VirtualEntityType.HAPPY_GHAST);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_LEASH_HOLDER, false);
        this.entityData.define(STAYS_STILL, false);
    }

    @Override
    public void setIsLeashHolder(boolean isLeashHolder) {
        entityData.set(IS_LEASH_HOLDER, isLeashHolder);
    }
    @Override
    public void setStaysStill(boolean staysStill) {
        entityData.set(STAYS_STILL, staysStill);
    }
    @Override
    public boolean isLeashHolder() {
        return entityData.get(IS_LEASH_HOLDER);
    }
    @Override
    public boolean isStaysStill() {
        return entityData.get(STAYS_STILL);
    }

    static {
        IS_LEASH_HOLDER = Mappings.findAccessor("HappyGhast", "IS_LEASH_HOLDER");
        STAYS_STILL = Mappings.findAccessor("HappyGhast", "STAYS_STILL");
    }
}