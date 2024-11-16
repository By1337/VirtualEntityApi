package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.DyeColor;

public class VirtualCatImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualCat {
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID;
    private static final EntityDataAccessor<Boolean> IS_LYING;
    private static final EntityDataAccessor<Boolean> RELAX_STATE_ONE;
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;

    public VirtualCatImpl() {
        super(VirtualEntityType.CAT);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 1);
        this.entityData.define(IS_LYING, false);
        this.entityData.define(RELAX_STATE_ONE, false);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.ordinal());
    }

    @Override
    public int getCatType() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    @Override
    public void setCatType(int type) {
        if (type < 0 || type >= 11) {
            type = 10;
        }
        this.entityData.set(DATA_TYPE_ID, type);
    }

    @Override
    public void setLying(boolean flag) {
        this.entityData.set(IS_LYING, flag);
    }

    @Override
    public boolean isLying() {
        return this.entityData.get(IS_LYING);
    }

    @Override
    public void setRelaxStateOne(boolean flag) {
        this.entityData.set(RELAX_STATE_ONE, flag);
    }

    @Override
    public boolean isRelaxStateOne() {
        return this.entityData.get(RELAX_STATE_ONE);
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.values()[(this.entityData.get(DATA_COLLAR_COLOR))];
    }

    @Override
    public void setCollarColor(DyeColor dyeColor) {
        this.entityData.set(DATA_COLLAR_COLOR, dyeColor.ordinal());
    }


    static {
        DATA_TYPE_ID = Mappings.findAccessor("Cat", "DATA_TYPE_ID");
        IS_LYING = Mappings.findAccessor("Cat", "IS_LYING");
        RELAX_STATE_ONE = Mappings.findAccessor("Cat", "RELAX_STATE_ONE");
        DATA_COLLAR_COLOR = Mappings.findAccessor("Cat", "DATA_COLLAR_COLOR");
    }
}