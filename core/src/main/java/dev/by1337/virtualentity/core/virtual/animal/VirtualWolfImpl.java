package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.WolfSoundVariant;
import dev.by1337.virtualentity.api.entity.WolfVariant;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

public class VirtualWolfImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualWolf {
    private static final boolean NEWER_OR_EQUAL_1_20_6 = Version.VERSION.newerThanOrEqual(Version.V1_20_6);
    private static final EntityDataAccessor<Boolean> DATA_INTERESTED_ID;
    private static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR;
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME;
    @SinceMinecraftVersion("1.20.6")
    private static final EntityDataAccessor<WolfVariant> DATA_VARIANT_ID;
    @SinceMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<WolfSoundVariant> DATA_SOUND_VARIANT_ID;

    public VirtualWolfImpl() {
        super(VirtualEntityType.WOLF);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_INTERESTED_ID, false);
        this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        if (NEWER_OR_EQUAL_1_20_6) {
            entityData.define(DATA_VARIANT_ID, WolfVariant.PALE);
        }
        if (DATA_SOUND_VARIANT_ID != null) {
            entityData.define(DATA_SOUND_VARIANT_ID, WolfSoundVariant.CLASSIC);
        }
    }

    @SinceMinecraftVersion("1.21.5")
    public void setSoundVariant(WolfSoundVariant variant) {
        entityData.set(DATA_SOUND_VARIANT_ID, variant);
    }

    @SinceMinecraftVersion("1.21.5")
    public WolfSoundVariant getSoundVariant() {
        return entityData.get(DATA_SOUND_VARIANT_ID);
    }

    @Override
    @SinceMinecraftVersion("1.20.6")
    public void setWolfVariant(WolfVariant variant) {
        this.entityData.define(DATA_VARIANT_ID, variant);
    }

    @Override
    @SinceMinecraftVersion("1.20.6")
    public WolfVariant getWolfVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
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
        if (NEWER_OR_EQUAL_1_20_6) {
            DATA_VARIANT_ID = Mappings.findAccessor("Wolf", "DATA_VARIANT_ID");
        } else {
            DATA_VARIANT_ID = null;
        }
        DATA_INTERESTED_ID = Mappings.findAccessor("Wolf", "DATA_INTERESTED_ID");
        DATA_COLLAR_COLOR = Mappings.findAccessor("Wolf", "DATA_COLLAR_COLOR");
        DATA_REMAINING_ANGER_TIME = Mappings.findAccessor("Wolf", "DATA_REMAINING_ANGER_TIME");
        if (Version.is1_21_5orNewer()) {
            DATA_SOUND_VARIANT_ID = Mappings.findAccessor("Wolf", "DATA_SOUND_VARIANT_ID");
        } else {
            DATA_SOUND_VARIANT_ID = null;
        }
    }
}
