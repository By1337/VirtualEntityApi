package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.PigVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

public class VirtualPigImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualPig {
    @Deprecated
    @Nullable
    @RemovedInMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    @SinceMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<PigVariant> DATA_VARIANT_ID;

    public VirtualPigImpl() {
        super(VirtualEntityType.PIG);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (DATA_SADDLE_ID != null) {
            this.entityData.define(DATA_SADDLE_ID, false);
        } else {
            entityData.define(DATA_VARIANT_ID, PigVariant.TEMPERATE);
        }
        this.entityData.define(DATA_BOOST_TIME, 0);
    }

    @SinceMinecraftVersion("1.21.5")
    public void setVariant(PigVariant variant) {
        if (DATA_VARIANT_ID == null) return;
        this.entityData.define(DATA_VARIANT_ID, variant);
    }

    @SinceMinecraftVersion("1.21.5")
    public PigVariant getVariant() {
        if (DATA_VARIANT_ID == null) return PigVariant.TEMPERATE;
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
        if (DATA_SADDLE_ID == null) return false;
        return entityData.get(DATA_SADDLE_ID);
    }

    @Override
    public void setSaddle(boolean flag) {
        if (DATA_SADDLE_ID == null) return;
        entityData.set(DATA_SADDLE_ID, flag);
    }

    static {
        DATA_BOOST_TIME = Mappings.findAccessor("Pig", "DATA_BOOST_TIME");

        if (Version.is1_21_5orNewer()) {
            DATA_VARIANT_ID = Mappings.findAccessor("Pig", "DATA_VARIANT_ID");
            DATA_SADDLE_ID = null;
        } else {
            DATA_SADDLE_ID = Mappings.findAccessor("Pig", "DATA_SADDLE_ID");
            DATA_VARIANT_ID = null;
        }
    }
}
