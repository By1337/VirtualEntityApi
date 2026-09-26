package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.PigSoundVariant;
import dev.by1337.virtualentity.api.entity.PigVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualPigImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualPig {
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;

    private static final EntityDataAccessor<PigVariant> DATA_VARIANT_ID;
    @SinceMinecraftVersion("26.1")
    private static final EntityDataAccessor<PigSoundVariant> DATA_SOUND_VARIANT_ID;

    public VirtualPigImpl() {
        super(VirtualEntityType.PIG);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_VARIANT_ID, PigVariant.TEMPERATE);
        entityData.define(DATA_BOOST_TIME, 0);
        if (ServerVersion.is26_1orNewer()) {
            entityData.define(DATA_SOUND_VARIANT_ID, PigSoundVariant.CLASSIC);
        }
    }
    @SinceMinecraftVersion("26.1")
    public PigSoundVariant getSoundVariant() {
        return entityData.get(DATA_SOUND_VARIANT_ID);
    }
    @SinceMinecraftVersion("26.1")
    public void setSoundVariant(PigSoundVariant soundVariant) {
        entityData.set(DATA_SOUND_VARIANT_ID, soundVariant);
    }

    public void setVariant(PigVariant variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    public PigVariant getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public int getBoostTime() {
        return entityData.get(DATA_BOOST_TIME);
    }

    @Override
    public void setBoostTime(int boostTime) {
        entityData.set(DATA_BOOST_TIME, boostTime);
    }

    @Override
    public boolean isSaddle() {
        return false;
    }

    @Override
    public void setSaddle(boolean flag) {
        return;
    }

    static {
        DATA_BOOST_TIME = Mappings.findAccessor("Pig", "DATA_BOOST_TIME");

        DATA_VARIANT_ID = Mappings.findAccessor("Pig", "DATA_VARIANT_ID");

        if (ServerVersion.is26_1orNewer()) {
            DATA_SOUND_VARIANT_ID = Mappings.findAccessor("Pig", "DATA_SOUND_VARIANT_ID");
        } else {
            DATA_SOUND_VARIANT_ID = null;
        }
    }
}
