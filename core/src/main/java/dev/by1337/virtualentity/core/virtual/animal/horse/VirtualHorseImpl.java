package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualHorseImpl extends VirtualAbstractHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualHorse {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT;

    public VirtualHorseImpl() {
        super(VirtualEntityType.HORSE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    @Override
    public void setTypeVariant(int typeVariant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, typeVariant);
    }

    @Override
    public int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    static {
        DATA_ID_TYPE_VARIANT = Mappings.findAccessor("Horse", "DATA_ID_TYPE_VARIANT");
    }
}
