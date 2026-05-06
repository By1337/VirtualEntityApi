package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.CowSoundVariant;
import dev.by1337.virtualentity.api.entity.CowVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.util.Version;

public class VirtualCowImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualCow {

    @SinceMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<CowVariant> DATA_VARIANT_ID;
    @SinceMinecraftVersion("26.1")
    private static final EntityDataAccessor<CowSoundVariant> DATA_SOUND_VARIANT_ID;

    public VirtualCowImpl() {
        super(VirtualEntityType.COW);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (DATA_VARIANT_ID != null) {
            entityData.define(DATA_VARIANT_ID, CowVariant.TEMPERATE);
        }
        if (ServerVersion.is26_1orNewer()) {
            entityData.define(DATA_SOUND_VARIANT_ID, CowSoundVariant.CLASSIC);
        }
    }
    @SinceMinecraftVersion("26.1")
    public CowSoundVariant getSoundVariant() {
        return entityData.get(DATA_SOUND_VARIANT_ID);
    }
    @SinceMinecraftVersion("26.1")
    public void setSoundVariant(CowSoundVariant soundVariant) {
        entityData.set(DATA_SOUND_VARIANT_ID, soundVariant);
    }

    @SinceMinecraftVersion("1.21.5")
    public void setVariant(CowVariant variant) {
        entityData.set(DATA_VARIANT_ID, variant);
    }

    @SinceMinecraftVersion("1.21.5")
    public CowVariant getVariant() {
        return entityData.get(DATA_VARIANT_ID);
    }

    static {
        if (Version.is1_21_5orNewer()) {
            DATA_VARIANT_ID = Mappings.findAccessor("Cow", "DATA_VARIANT_ID");
        } else {
            DATA_VARIANT_ID = null;
        }
        if (ServerVersion.is26_1orNewer()) {
            DATA_SOUND_VARIANT_ID = Mappings.findAccessor("Cow", "DATA_SOUND_VARIANT_ID");
        } else {
            DATA_SOUND_VARIANT_ID = null;
        }
    }
}