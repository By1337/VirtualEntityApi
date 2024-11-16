package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualSnowGolemImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSnowGolem {
    private static final EntityDataAccessor<Byte> DATA_PUMPKIN_ID;

    public VirtualSnowGolemImpl() {
        super(VirtualEntityType.SNOW_GOLEM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PUMPKIN_ID, (byte) 16);
    }

    @Override
    public boolean hasPumpkin() {
        return (this.entityData.get(DATA_PUMPKIN_ID) & 16) != 0;
    }

    @Override
    public void setPumpkin(boolean flag) {
        byte var2 = this.entityData.get(DATA_PUMPKIN_ID);
        if (flag) {
            this.entityData.set(DATA_PUMPKIN_ID, (byte) (var2 | 16));
        } else {
            this.entityData.set(DATA_PUMPKIN_ID, (byte) (var2 & -17));
        }

    }

    static {
        DATA_PUMPKIN_ID = Mappings.findAccessor("SnowGolem", "DATA_PUMPKIN_ID");
    }
}
