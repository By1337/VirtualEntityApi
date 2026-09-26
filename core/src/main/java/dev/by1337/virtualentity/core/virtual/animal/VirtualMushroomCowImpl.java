package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.MushroomType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualMushroomCowImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualMushroom {

    private static final EntityDataAccessor<Integer> DATA_TYPE_INT;

    public VirtualMushroomCowImpl() {
        super(VirtualEntityType.MOOSHROOM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_TYPE_INT, MushroomType.RED.ordinal());
    }

    public MushroomType getMushroomType() {
        return MushroomType.byId(entityData.get(DATA_TYPE_INT));
    }

    public void setMushroomType(MushroomType type) {
        entityData.set(DATA_TYPE_INT, type.ordinal());
    }

    static {
        DATA_TYPE_INT = Mappings.findAccessor("MushroomCow", "DATA_TYPE");

    }
}
