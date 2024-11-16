package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualPhantomImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualPhantom {
    private static final EntityDataAccessor<Integer> ID_SIZE;

    public VirtualPhantomImpl() {
        super(VirtualEntityType.PHANTOM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 0);
    }

    // 0 - 64
    @Override
    public void setPhantomSize(int size) {
        this.entityData.set(ID_SIZE, clamp(size, 0, 64));
    }

    @Override
    public int getPhantomSize() {
        return this.entityData.get(ID_SIZE);
    }

    public static int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        } else {
            return Math.min(val, max);
        }
    }

    static {
        ID_SIZE = Mappings.findAccessor("Phantom", "ID_SIZE");
    }
}
