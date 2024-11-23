package dev.by1337.virtualentity.core.virtual.animal.camel;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.animal.horse.VirtualAbstractHorseImpl;

@SinceMinecraftVersion("1.19.4")
public class VirtualCamelImpl extends VirtualAbstractHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.camel.VirtualCamel {
    private static final EntityDataAccessor<Boolean> DASH;
    private static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK;

    public VirtualCamelImpl() {
        super(VirtualEntityType.CAMEL);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DASH, false);
        this.entityData.define(LAST_POSE_CHANGE_TICK, 0L);
    }

    @Override
    public boolean isDashing() {
        return this.entityData.get(DASH);
    }

    @Override
    public void setDashing(boolean flag) {
        this.entityData.set(DASH, flag);
    }
    @Override
    public long getLastPoseChangeTick() {
        return this.entityData.get(LAST_POSE_CHANGE_TICK);
    }
    @Override
    public void setLastPoseChangeTick(long tick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, tick);
    }

    static {
        DASH = Mappings.findAccessor("Camel", "DASH");
        LAST_POSE_CHANGE_TICK = Mappings.findAccessor("Camel", "LAST_POSE_CHANGE_TICK");
    }
}