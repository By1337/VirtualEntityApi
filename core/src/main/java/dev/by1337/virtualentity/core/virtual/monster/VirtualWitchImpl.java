package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualWitch;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.raid.VirtualRaiderImpl;

public class VirtualWitchImpl extends VirtualRaiderImpl implements VirtualWitch {
    private static final EntityDataAccessor<Boolean> DATA_USING_ITEM;

    public VirtualWitchImpl() {
        super(VirtualEntityType.WITCH);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_USING_ITEM, false);
    }

    @Override
    public void setUsingItem(boolean flag) {
        this.entityData.set(DATA_USING_ITEM, flag);
    }

    @Override
    public boolean isDrinkingPotion() {
        return this.entityData.get(DATA_USING_ITEM);
    }

    static {
        DATA_USING_ITEM = Mappings.findAccessor("Witch", "DATA_USING_ITEM");
    }
}