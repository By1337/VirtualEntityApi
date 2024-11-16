package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualCreeper;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualCreeperImpl extends VirtualMobImpl implements VirtualCreeper {
    private static final EntityDataAccessor<Integer> DATA_SWELL_DIR;
    private static final EntityDataAccessor<Boolean> DATA_IS_POWERED;
    private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED;

    public VirtualCreeperImpl() {
        super(VirtualEntityType.CREEPER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SWELL_DIR, -1);
        this.entityData.define(DATA_IS_POWERED, false);
        this.entityData.define(DATA_IS_IGNITED, false);
    }

    @Override
    public boolean isPowered() {
        return this.entityData.get(DATA_IS_POWERED);
    }

    @Override
    public int getSwellDir() {
        return this.entityData.get(DATA_SWELL_DIR);
    }

    @Override
    public void setSwellDir(int i) {
        this.entityData.set(DATA_SWELL_DIR, i);
    }

    @Override
    public void setPowered(boolean powered) {
        this.entityData.set(DATA_IS_POWERED, powered);
    }

    @Override
    public boolean isIgnited() {
        return this.entityData.get(DATA_IS_IGNITED);
    }

    @Override
    public void ignite() {
        this.setIgnited(true);
    }

    @Override
    public void setIgnited(boolean ignited) {
        this.entityData.set(DATA_IS_IGNITED, ignited);
    }

    static {
        DATA_SWELL_DIR = Mappings.findAccessor("Creeper", "DATA_SWELL_DIR");
        DATA_IS_POWERED = Mappings.findAccessor("Creeper", "DATA_IS_POWERED");
        DATA_IS_IGNITED = Mappings.findAccessor("Creeper", "DATA_IS_IGNITED");
    }
}
