package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.CatSoundVariant;
import dev.by1337.virtualentity.api.entity.CatVariant;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualCatImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualCat {

    private static final EntityDataAccessor<CatVariant> DATA_VARIANT_ID;
    private static final EntityDataAccessor<Boolean> IS_LYING;
    private static final EntityDataAccessor<Boolean> RELAX_STATE_ONE;
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;
    @SinceMinecraftVersion("26.1")
    private static final EntityDataAccessor<CatSoundVariant> DATA_SOUND_VARIANT_ID;

    public VirtualCatImpl() {
        super(VirtualEntityType.CAT);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, CatVariant.RED);
        this.entityData.define(IS_LYING, false);
        this.entityData.define(RELAX_STATE_ONE, false);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
        if (ServerVersion.is26_1orNewer()) {
            this.entityData.define(DATA_SOUND_VARIANT_ID, CatSoundVariant.CLASSIC);
        }
    }
    @SinceMinecraftVersion("26.1")
    public CatSoundVariant getSoundVariant() {
        return this.entityData.get(DATA_SOUND_VARIANT_ID);
    }
    @SinceMinecraftVersion("26.1")
    public void setSoundVariant(CatSoundVariant variant) {
        this.entityData.set(DATA_SOUND_VARIANT_ID, variant);
    }

    public CatVariant getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public void setVariant(CatVariant variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    @Override
    @RemovedInMinecraftVersion("1.19.4")
    public int getCatType() {
        return getVariant().getId();
    }

    @Override
    @RemovedInMinecraftVersion("1.19.4")
    public void setCatType(int type) {
        CatVariant[] arr = CatVariant.values();
        if (type < 0 || type >= arr.length) {
            type = arr.length - 1;
        }
        this.entityData.set(DATA_VARIANT_ID, arr[type]);
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

        DATA_VARIANT_ID = Mappings.findAccessor("Cat", "DATA_VARIANT_ID");
        IS_LYING = Mappings.findAccessor("Cat", "IS_LYING");
        RELAX_STATE_ONE = Mappings.findAccessor("Cat", "RELAX_STATE_ONE");
        DATA_COLLAR_COLOR = Mappings.findAccessor("Cat", "DATA_COLLAR_COLOR");
        if (ServerVersion.is26_1orNewer()) {
            DATA_SOUND_VARIANT_ID = Mappings.findAccessor("Cat", "DATA_SOUND_VARIANT_ID");
        }else {
            DATA_SOUND_VARIANT_ID = null;
        }
    }
}
