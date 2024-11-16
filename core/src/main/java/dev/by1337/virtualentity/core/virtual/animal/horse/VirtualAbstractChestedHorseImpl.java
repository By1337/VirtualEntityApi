package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public abstract class VirtualAbstractChestedHorseImpl extends VirtualAbstractHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualAbstractChestedHorse {
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST;

    protected VirtualAbstractChestedHorseImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_CHEST, false);
    }

    @Override
    public boolean hasChest() {
        return this.entityData.get(DATA_ID_CHEST);
    }

    @Override
    public void setChest(boolean flag) {
        this.entityData.set(DATA_ID_CHEST, flag);
    }

    static {
        DATA_ID_CHEST = Mappings.findAccessor("AbstractChestedHorse", "DATA_ID_CHEST");
    }
}
