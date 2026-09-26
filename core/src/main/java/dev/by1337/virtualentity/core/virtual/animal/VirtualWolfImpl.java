package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.WolfSoundVariant;
import dev.by1337.virtualentity.api.entity.WolfVariant;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualWolfImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualWolf {
    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID;
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;

    private static final EntityDataAccessor<Long> DATA_ANGER_END_TIME;

    private static final EntityDataAccessor<WolfVariant> DATA_VARIANT_ID;

    private static final EntityDataAccessor<WolfSoundVariant> DATA_SOUND_VARIANT_ID;

    public VirtualWolfImpl() {
        super(VirtualEntityType.WOLF);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_INTERESTED_ID, false);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
        this.entityData.define(DATA_ANGER_END_TIME, -1L);
        entityData.define(DATA_VARIANT_ID, WolfVariant.PALE);
        entityData.define(DATA_SOUND_VARIANT_ID, WolfSoundVariant.CLASSIC);
    }

    public void setSoundVariant(WolfSoundVariant variant) {
        entityData.set(DATA_SOUND_VARIANT_ID, variant);
    }

    public WolfSoundVariant getSoundVariant() {
        return entityData.get(DATA_SOUND_VARIANT_ID);
    }

    @Override
    public void setWolfVariant(WolfVariant variant) {
        this.entityData.define(DATA_VARIANT_ID, variant);
    }

    @Override
    public WolfVariant getWolfVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    public long getAngerEndTime() {
        return this.entityData.get(DATA_ANGER_END_TIME);
    }

    public void setAngerEndTime(long time) {
        this.entityData.set(DATA_ANGER_END_TIME, time);
    }

    @Deprecated
    @RemovedInMinecraftVersion("1.21.11")
    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Deprecated
    @RemovedInMinecraftVersion("1.21.11")
    @Override
    public void setRemainingPersistentAngerTime(int time) {
        return;
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.values()[this.entityData.get(DATA_COLLAR_COLOR)];
    }

    @Override
    public void setCollarColor(DyeColor color) {
        this.entityData.set(DATA_COLLAR_COLOR, color.getId());
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
        DATA_VARIANT_ID = Mappings.findAccessor("Wolf", "DATA_VARIANT_ID");
        DATA_INTERESTED_ID = Mappings.findAccessor("Wolf", "DATA_INTERESTED_ID");
        DATA_COLLAR_COLOR = Mappings.findAccessor("Wolf", "DATA_COLLAR_COLOR");

        DATA_ANGER_END_TIME = Mappings.findAccessor("Wolf", "DATA_ANGER_END_TIME");
        DATA_SOUND_VARIANT_ID = Mappings.findAccessor("Wolf", "DATA_SOUND_VARIANT_ID");
    }
}
