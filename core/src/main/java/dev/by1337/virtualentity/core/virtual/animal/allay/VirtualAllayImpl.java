package dev.by1337.virtualentity.core.virtual.animal.allay;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.19.4")
public class VirtualAllayImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.animal.allay.VirtualAllay {
    private static final EntityDataAccessor<Boolean> DATA_DANCING;
    private static final EntityDataAccessor<Boolean> DATA_CAN_DUPLICATE;

    public VirtualAllayImpl() {
        super(VirtualEntityType.ALLAY);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_DANCING, false);
        entityData.define(DATA_CAN_DUPLICATE, false);
    }

    @Override
    public void setDancing(final boolean dancing) {
        entityData.set(DATA_DANCING, dancing);
    }

    @Override
    public boolean isDancing() {
        return entityData.get(DATA_DANCING);
    }

    @Override
    public void setCanDuplicate(final boolean canDuplicate) {
        entityData.set(DATA_CAN_DUPLICATE, canDuplicate);
    }

    @Override
    public boolean isCanDuplicate() {
        return entityData.get(DATA_CAN_DUPLICATE);
    }

    static {
        DATA_DANCING = Mappings.findAccessor("Allay", "DATA_DANCING");
        DATA_CAN_DUPLICATE = Mappings.findAccessor("Allay", "DATA_CAN_DUPLICATE");
    }
}