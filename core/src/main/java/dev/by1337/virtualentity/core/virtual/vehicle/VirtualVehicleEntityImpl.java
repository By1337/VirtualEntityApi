package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public abstract class VirtualVehicleEntityImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualVehicleEntity {
    private static final EntityDataAccessor<Integer> DATA_ID_HURT = Mappings.findAccessor("VehicleEntity", "DATA_ID_HURT");
    private static final EntityDataAccessor<Integer> DATA_ID_HURTDIR = Mappings.findAccessor("VehicleEntity", "DATA_ID_HURTDIR");
    private static final EntityDataAccessor<Float> DATA_ID_DAMAGE = Mappings.findAccessor("VehicleEntity", "DATA_ID_DAMAGE");

    public VirtualVehicleEntityImpl(VirtualEntityType type) { super(type); }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_ID_HURT, 0);
        entityData.define(DATA_ID_HURTDIR, 1);
        entityData.define(DATA_ID_DAMAGE, 0.0F);
    }

    @Override public int getHurt() { return entityData.get(DATA_ID_HURT); }
    @Override public void setHurt(int hurt) { entityData.set(DATA_ID_HURT, hurt); }
    @Override public int getHurtDirection() { return entityData.get(DATA_ID_HURTDIR); }
    @Override public void setHurtDirection(int direction) { entityData.set(DATA_ID_HURTDIR, direction); }
    @Override public float getDamage() { return entityData.get(DATA_ID_DAMAGE); }
    @Override public void setDamage(float damage) { entityData.set(DATA_ID_DAMAGE, damage); }
}
