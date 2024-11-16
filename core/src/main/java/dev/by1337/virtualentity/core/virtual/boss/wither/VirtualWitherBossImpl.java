package dev.by1337.virtualentity.core.virtual.boss.wither;

import com.google.common.collect.ImmutableList;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

import java.util.List;

public class VirtualWitherBossImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.boss.wither.VirtualWitherBoss {
    private static final EntityDataAccessor<Integer> DATA_TARGET_A;
    private static final EntityDataAccessor<Integer> DATA_TARGET_B;
    private static final EntityDataAccessor<Integer> DATA_TARGET_C;
    private static final EntityDataAccessor<Integer> DATA_ID_INV;
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS;

    public VirtualWitherBossImpl() {
        super(VirtualEntityType.WITHER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TARGET_A, 0);
        this.entityData.define(DATA_TARGET_B, 0);
        this.entityData.define(DATA_TARGET_C, 0);
        this.entityData.define(DATA_ID_INV, 0);
    }

    @Override
    public int getInvulnerableTicks() {
        return this.entityData.get(DATA_ID_INV);
    }

    @Override
    public void setInvulnerableTicks(int ticks) {
        this.entityData.set(DATA_ID_INV, ticks);
    }

    @Override
    public int getAlternativeTarget(int target) {
        return this.entityData.get(DATA_TARGETS.get(target));
    }

    @Override
    public void setAlternativeTarget(int target, int value) {
        this.entityData.set(DATA_TARGETS.get(target), value);
    }

    static {
        DATA_TARGET_A = Mappings.findAccessor("WitherBoss", "DATA_TARGET_A");
        DATA_TARGET_B = Mappings.findAccessor("WitherBoss", "DATA_TARGET_B");
        DATA_TARGET_C = Mappings.findAccessor("WitherBoss", "DATA_TARGET_C");
        DATA_ID_INV = Mappings.findAccessor("WitherBoss", "DATA_ID_INV");
        DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C);
    }
}
