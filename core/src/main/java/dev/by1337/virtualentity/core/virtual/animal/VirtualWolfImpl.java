package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualWolfImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualWolf {
    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID;
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME;

    public VirtualWolfImpl() {
        super(VirtualEntityType.WOLF);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_INTERESTED_ID, false);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.ordinal());
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
    public DyeColor getCollarColor() {
        return DyeColor.values()[this.entityData.get(DATA_COLLAR_COLOR)];
    }

    @Override
    public void setCollarColor(DyeColor color) {
        this.entityData.set(DATA_COLLAR_COLOR, color.ordinal());
    }

    @Override
    public void setIsInterested(boolean flag) {
        this.entityData.set(DATA_INTERESTED_ID, flag);
    }

    @Override
    public boolean isInterested() {
        return this.entityData.get(DATA_INTERESTED_ID);
    }

    static {
        DATA_INTERESTED_ID = Mappings.findAccessor("Wolf", "DATA_INTERESTED_ID");
        DATA_COLLAR_COLOR = Mappings.findAccessor("Wolf", "DATA_COLLAR_COLOR");
        DATA_REMAINING_ANGER_TIME = Mappings.findAccessor("Wolf", "DATA_REMAINING_ANGER_TIME");
    }
}
