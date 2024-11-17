package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualSheepImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSheep {
    private static final EntityDataAccessor<Byte> DATA_WOOL_ID;

    public VirtualSheepImpl() {
        super(VirtualEntityType.SHEEP);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_WOOL_ID, (byte) 0);
    }

    @Override
    public void setColor(DyeColor color) {
        byte var2 = this.entityData.get(DATA_WOOL_ID);
        this.entityData.set(DATA_WOOL_ID, (byte) (var2 & 240 | color.getId() & 15));
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.values()[(this.entityData.get(DATA_WOOL_ID) & 15)];
    }

    @Override
    public boolean isSheared() {
        return (this.entityData.get(DATA_WOOL_ID) & 16) != 0;
    }

    @Override
    public void setSheared(boolean param0) {
        byte var2 = this.entityData.get(DATA_WOOL_ID);
        if (param0) {
            this.entityData.set(DATA_WOOL_ID, (byte) (var2 | 16));
        } else {
            this.entityData.set(DATA_WOOL_ID, (byte) (var2 & -17));
        }
    }

    static {
        DATA_WOOL_ID = Mappings.findAccessor("Sheep", "DATA_WOOL_ID");
    }
}
