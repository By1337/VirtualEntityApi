package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualParrotImpl extends VirtualTamableAnimalImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualParrot {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID;

    public VirtualParrotImpl() {
        super(VirtualEntityType.PARROT);
    }

    @Override
    public int getVariant() {
        return clamp(this.entityData.get(DATA_VARIANT_ID), 0, 4);
    }
    public static int clamp(int source, int min, int max) {
        return source < min ? min : Math.min(source, max);
    }

    @Override
    public void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }

    static {
        DATA_VARIANT_ID = Mappings.findAccessor("Parrot", "DATA_VARIANT_ID");
    }
}
