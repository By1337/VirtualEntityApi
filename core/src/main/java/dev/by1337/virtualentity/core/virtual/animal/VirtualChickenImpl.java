package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ChickenVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.util.Version;

public class VirtualChickenImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualChicken {

    @SinceMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<ChickenVariant> DATA_VARIANT_ID;

    public VirtualChickenImpl() {
        super(VirtualEntityType.CHICKEN);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (DATA_VARIANT_ID != null) {
            entityData.define(DATA_VARIANT_ID, ChickenVariant.TEMPERATE);
        }
    }

    @SinceMinecraftVersion("1.21.5")
    public void setVariant(ChickenVariant variant) {
        entityData.set(DATA_VARIANT_ID, variant);
    }

    @SinceMinecraftVersion("1.21.5")
    public ChickenVariant getVariant() {
        return entityData.get(DATA_VARIANT_ID);
    }

    static {
        if (Version.is1_21_5orNewer()) {
            DATA_VARIANT_ID = Mappings.findAccessor("Chicken", "DATA_VARIANT_ID");
        } else {
            DATA_VARIANT_ID = null;
        }
    }
}