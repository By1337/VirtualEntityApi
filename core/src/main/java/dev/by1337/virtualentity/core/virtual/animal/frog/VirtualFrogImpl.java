package dev.by1337.virtualentity.core.virtual.animal.frog;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.FrogVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

import java.util.OptionalInt;

@SinceMinecraftVersion("1.19.4")
public class VirtualFrogImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.frog.VirtualFrog {
    private static final EntityDataAccessor<FrogVariant> DATA_VARIANT_ID;
    private static final EntityDataAccessor<OptionalInt> DATA_TONGUE_TARGET_ID;

    public VirtualFrogImpl() {
        super(VirtualEntityType.FROG);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_VARIANT_ID, FrogVariant.TEMPERATE);
        entityData.define(DATA_TONGUE_TARGET_ID, OptionalInt.empty());
    }

    @Override
    public void setVariantId(final FrogVariant variant) {
        entityData.set(DATA_VARIANT_ID, variant);
    }

    @Override
    public FrogVariant getVariantId() {
        return entityData.get(DATA_VARIANT_ID);
    }

    @Override
    public void setTargetId(int targetId) {
        if (targetId == -1) {
            entityData.set(DATA_TONGUE_TARGET_ID, OptionalInt.empty());
        } else {
            entityData.set(DATA_TONGUE_TARGET_ID, OptionalInt.of(targetId));
        }
    }

    @Override
    public OptionalInt getTargetId() {
        return entityData.get(DATA_TONGUE_TARGET_ID);
    }

    static {
        DATA_VARIANT_ID = Mappings.findAccessor("Frog", "DATA_VARIANT_ID");
        DATA_TONGUE_TARGET_ID = Mappings.findAccessor("Frog", "DATA_TONGUE_TARGET_ID");
    }
}