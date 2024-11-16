package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualTropicalFishImpl extends VirtualAbstractFishImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualTropicalFish {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT;

    public VirtualTropicalFishImpl() {
        super(VirtualEntityType.TROPICAL_FISH);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    public void setVariant(int variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant);
    }

    @Override
    public int getVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    static {
        DATA_ID_TYPE_VARIANT = Mappings.findAccessor("TropicalFish", "DATA_ID_TYPE_VARIANT");
    }
}
