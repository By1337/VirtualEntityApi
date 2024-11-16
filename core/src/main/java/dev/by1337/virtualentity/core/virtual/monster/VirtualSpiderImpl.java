package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualSpiderImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualSpider {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    protected VirtualSpiderImpl(VirtualEntityType type) {
        super(type);
    }

    public VirtualSpiderImpl() {
        super(VirtualEntityType.SPIDER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
    }

    @Override
    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    @Override
    public void setClimbing(boolean flag) {
        byte var2 = this.entityData.get(DATA_FLAGS_ID);
        if (flag) {
            var2 = (byte)(var2 | 1);
        } else {
            var2 &= -2;
        }

        this.entityData.set(DATA_FLAGS_ID, var2);
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("Spider", "DATA_FLAGS_ID");
    }
}
