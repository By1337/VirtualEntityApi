package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.SalmonVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualSalmonImpl extends VirtualAbstractFishImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSalmon {

    private static final EntityDataAccessor<Integer> DATA_TYPE_INT;

    public VirtualSalmonImpl() {
        super(VirtualEntityType.SALMON);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_TYPE_INT, SalmonVariant.MEDIUM.ordinal());
    }

    public void setType(final SalmonVariant type) {
        entityData.define(DATA_TYPE_INT, type.ordinal());
    }

    static {
        DATA_TYPE_INT = Mappings.findAccessor("Salmon", "DATA_TYPE");

    }
}
