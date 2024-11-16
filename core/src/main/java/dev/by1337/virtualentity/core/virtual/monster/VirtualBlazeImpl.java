package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualBlazeImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualBlaze {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    public VirtualBlazeImpl() {
        super(VirtualEntityType.BLAZE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
    }

    @Override
    public boolean isCharged() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    @Override
    public void setCharged(boolean flag) {
        byte var2 = this.entityData.get(DATA_FLAGS_ID);
        if (flag) {
            var2 = (byte) (var2 | 1);
        } else {
            var2 &= -2;
        }

        this.entityData.set(DATA_FLAGS_ID, var2);
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("Blaze", "DATA_FLAGS_ID");
    }
}
