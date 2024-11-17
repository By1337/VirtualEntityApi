package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualRabbitImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualRabbit {
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID;

    public VirtualRabbitImpl() {
        super(VirtualEntityType.RABBIT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    @Override
    public int getRabbitType() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    // 0 - 5
    // 99 = KILLER_BUNNY
    @Override
    public void setRabbitType(int type) {
        this.entityData.set(DATA_TYPE_ID, type);
    }

    static {
        DATA_TYPE_ID = Mappings.findAccessor("Rabbit", "DATA_TYPE_ID");
    }
}
