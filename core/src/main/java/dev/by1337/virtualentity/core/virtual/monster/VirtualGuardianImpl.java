package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualGuardian;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualGuardianImpl extends VirtualMobImpl implements VirtualGuardian {
    private static final EntityDataAccessor<Boolean> DATA_ID_MOVING;
    private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET;

    public VirtualGuardianImpl() {
        super(VirtualEntityType.GUARDIAN);
    }

    protected VirtualGuardianImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_MOVING, false);
        this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
    }

    @Override
    public boolean isMoving() {
        return this.entityData.get(DATA_ID_MOVING);
    }

    @Override
    public void setMoving(boolean flag) {
        this.entityData.set(DATA_ID_MOVING, flag);
    }

    @Override
    public void setActiveAttackTarget(int entityId) {
        this.entityData.set(DATA_ID_ATTACK_TARGET, entityId);
    }

    @Override
    public int getActiveAttackTarget() {
        return this.entityData.get(DATA_ID_ATTACK_TARGET);
    }

    @Override
    public boolean hasActiveAttackTarget() {
        return getActiveAttackTarget() != 0;
    }

    static {
        DATA_ID_MOVING = Mappings.findAccessor("Guardian", "DATA_ID_MOVING");
        DATA_ID_ATTACK_TARGET = Mappings.findAccessor("Guardian", "DATA_ID_ATTACK_TARGET");
    }
}
