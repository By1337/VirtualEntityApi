package dev.by1337.virtualentity.core.virtual.monster.creaking;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.21.3")
public class VirtualCreakingImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.creaking.VirtualCreaking {
    private static final EntityDataAccessor<Boolean> CAN_MOVE;
    private static final EntityDataAccessor<Boolean> IS_ACTIVE;

    public VirtualCreakingImpl() {
        super(VirtualEntityType.CREAKING);
    }

    public VirtualCreakingImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(CAN_MOVE, true);
        entityData.define(IS_ACTIVE, false);
    }

    @Override
    public void setIsActive(boolean isActive) {
        entityData.set(IS_ACTIVE, isActive);
    }

    @Override
    public boolean isIsActive() {
        return entityData.get(IS_ACTIVE);
    }

    @Override
    public void setCanMove(boolean canMove) {
        entityData.set(CAN_MOVE, canMove);
    }

    @Override
    public boolean isCanMove() {
        return entityData.get(CAN_MOVE);
    }

    static {
        CAN_MOVE = Mappings.findAccessor("Creaking", "CAN_MOVE");
        IS_ACTIVE = Mappings.findAccessor("Creaking", "IS_ACTIVE");
    }
}