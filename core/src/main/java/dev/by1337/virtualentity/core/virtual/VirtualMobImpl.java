package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public abstract class VirtualMobImpl extends VirtualLivingEntityImpl implements VirtualMob {
    private static final EntityDataAccessor<Byte> DATA_MOB_FLAGS_ID;

    public VirtualMobImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MOB_FLAGS_ID, (byte) 0);
    }

    public void setNoAi(boolean flag) {
        byte current = this.entityData.get(DATA_MOB_FLAGS_ID);
        this.entityData.set(DATA_MOB_FLAGS_ID, flag ? (byte) (current | 0x1) : (byte) (current & -2));
    }

    public void setLeftHanded(boolean flag) {
        byte current = this.entityData.get(DATA_MOB_FLAGS_ID);
        this.entityData.set(DATA_MOB_FLAGS_ID, flag ? (byte) (current | 0x2) : (byte) (current & -3));
    }

    public void setAggressive(boolean flag) {
        byte current = this.entityData.get(DATA_MOB_FLAGS_ID);
        this.entityData.set(DATA_MOB_FLAGS_ID, flag ? (byte) (current | 0x4) : (byte) (current & -5));
    }

    public boolean isNoAi() {
        return (this.entityData.get(DATA_MOB_FLAGS_ID) & 0x1) != 0;
    }

    public boolean isLeftHanded() {
        return (this.entityData.get(DATA_MOB_FLAGS_ID) & 0x2) != 0;
    }

    public boolean isAggressive() {
        return (this.entityData.get(DATA_MOB_FLAGS_ID) & 0x4) != 0;
    }

    static {
        DATA_MOB_FLAGS_ID = Mappings.findAccessor("Mob", "DATA_MOB_FLAGS_ID");
    }
}
