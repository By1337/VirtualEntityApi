package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.MushroomType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

public class VirtualMushroomCowImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualMushroom {
    private static final EntityDataAccessor<String> DATA_TYPE;

    public VirtualMushroomCowImpl() {
        super(VirtualEntityType.MOOSHROOM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, MushroomType.RED.type());
    }

    public MushroomType getMushroomType() {
        return MushroomType.idToType.get(this.entityData.get(VirtualMushroomCowImpl.DATA_TYPE));
    }

    public void setMushroomType(MushroomType type) {
        this.entityData.set(VirtualMushroomCowImpl.DATA_TYPE, type.type());
    }

    static {
        DATA_TYPE = Mappings.findAccessor("MushroomCow", "DATA_TYPE");
    }
}
