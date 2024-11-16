package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualBat;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualBatImpl extends VirtualMobImpl implements VirtualBat {
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS;

    public VirtualBatImpl() {
        super(VirtualEntityType.BAT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte) 0);
    }

    public boolean isResting() {
        return (this.entityData.get(DATA_ID_FLAGS) & 0x1) != 0;
    }

    public void setResting(boolean flag) {
        byte current = this.entityData.get(DATA_ID_FLAGS);
        if (flag) {
            this.entityData.set(DATA_ID_FLAGS, (byte) (current | 0x1));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte) (current & -2));
        }
    }

    static {
        DATA_ID_FLAGS = Mappings.findAccessor("Bat", "DATA_ID_FLAGS");
    }
}
