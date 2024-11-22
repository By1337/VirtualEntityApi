package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.CatVariant;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

public class VirtualCatImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualCat {
    @RemovedInMinecraftVersion("1.19.4")
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID;
    @SinceMinecraftVersion("1.19.4")
    private static final EntityDataAccessor<CatVariant> DATA_VARIANT_ID;
    private static final EntityDataAccessor<Boolean> IS_LYING;
    private static final EntityDataAccessor<Boolean> RELAX_STATE_ONE;
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;

    public VirtualCatImpl() {
        super(VirtualEntityType.CAT);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            this.entityData.define(DATA_VARIANT_ID, CatVariant.RED);
        } else {
            this.entityData.define(DATA_TYPE_ID, 1);
        }
        this.entityData.define(IS_LYING, false);
        this.entityData.define(RELAX_STATE_ONE, false);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
    }

    @SinceMinecraftVersion("1.19.4")
    public CatVariant getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @SinceMinecraftVersion("1.19.4")
    public void setVariant(CatVariant variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    @Override
    @RemovedInMinecraftVersion("1.19.4")
    public int getCatType() {
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            return getVariant().getId();
        } else {
            return this.entityData.get(DATA_TYPE_ID);
        }
    }

    @Override
    @RemovedInMinecraftVersion("1.19.4")
    public void setCatType(int type) {
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            CatVariant[] arr = CatVariant.values();
            if (type < 0 || type >= arr.length) {
                type = arr.length - 1;
            }
            this.entityData.set(DATA_VARIANT_ID, arr[type]);
        } else {
            if (type < 0 || type >= 11) {
                type = 10;
            }
            this.entityData.set(DATA_TYPE_ID, type);
        }
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
        this.entityData.set(DATA_COLLAR_COLOR, dyeColor.getId());
    }


    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            DATA_TYPE_ID = null;
            DATA_VARIANT_ID = Mappings.findAccessor("Cat", "DATA_VARIANT_ID");
        } else {
            DATA_TYPE_ID = Mappings.findAccessor("Cat", "DATA_TYPE_ID");
            DATA_VARIANT_ID = null;
        }
        IS_LYING = Mappings.findAccessor("Cat", "IS_LYING");
        RELAX_STATE_ONE = Mappings.findAccessor("Cat", "RELAX_STATE_ONE");
        DATA_COLLAR_COLOR = Mappings.findAccessor("Cat", "DATA_COLLAR_COLOR");
    }
}