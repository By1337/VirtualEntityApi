package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualVexImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualVex {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    public VirtualVexImpl() {
        super(VirtualEntityType.VEX);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    @Override
    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    @Override
    public void setIsCharging(boolean flag) {
        this.setVexFlag(1, flag);
    }

    private boolean getVexFlag(int mask) {
        int var2 = this.entityData.get(DATA_FLAGS_ID);
        return (var2 & mask) != 0;
    }

    private void setVexFlag(int mask, boolean flag) {
        int var3 = this.entityData.get(DATA_FLAGS_ID);
        if (flag) {
            var3 |= mask;
        } else {
            var3 &= ~mask;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(var3 & 255));
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("Vex", "DATA_FLAGS_ID");
    }
}
