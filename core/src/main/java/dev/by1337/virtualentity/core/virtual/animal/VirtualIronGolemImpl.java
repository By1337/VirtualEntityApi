package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualIronGolemImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualIronGolem {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;

    public VirtualIronGolemImpl() {
        super(VirtualEntityType.IRON_GOLEM);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
    }

    @Override
    public boolean isPlayerCreated() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    @Override
    public void setCrackinessNone() {
        setHealth(20);
    }

    @Override
    public void setCrackinessLow() {
        setHealth(20f * 0.75f);
    }

    @Override
    public void setCrackinessMedium() {
        setHealth(20f * 0.5f);
    }

    @Override
    public void setCrackinessHigh() {
        setHealth(20f * 0.25f);
    }

    @Override
    public void setPlayerCreated(boolean flag) {
        byte var2 = this.entityData.get(DATA_FLAGS_ID);
        if (flag) {
            this.entityData.set(DATA_FLAGS_ID, (byte) (var2 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte) (var2 & -2));
        }
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("IronGolem", "DATA_FLAGS_ID");
    }
}