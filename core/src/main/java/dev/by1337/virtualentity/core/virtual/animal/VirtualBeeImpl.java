package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.animal.VirtualBee;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualBeeImpl extends VirtualAgeableMobImpl implements VirtualBee {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME;

    public VirtualBeeImpl() {
        super(VirtualEntityType.BEE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, time);
    }

    @Override
    public boolean hasStung() {
        return this.getFlag(0x4);
    }

    @Override
    public void setHasStung(boolean flag) {
        this.setFlag(0x4, flag);
    }

    @Override
    public boolean isRolling() {
        return this.getFlag(0x2);
    }

    @Override
    public void setRolling(boolean flag) {
        this.setFlag(0x2, flag);
    }

    private void setFlag(int mask, boolean flag) {
        if (flag) {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) | mask));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte)(this.entityData.get(DATA_FLAGS_ID) & ~mask));
        }
    }
    private boolean getFlag(int mask) {
        return (this.entityData.get(DATA_FLAGS_ID) & mask) != 0;
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("Bee", "DATA_FLAGS_ID");
        DATA_REMAINING_ANGER_TIME = Mappings.findAccessor("Bee", "DATA_REMAINING_ANGER_TIME");
    }
}
