package dev.by1337.virtualentity.core.virtual.boss.enderdragon;

import dev.by1337.virtualentity.api.entity.EnderDragonPhase;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualEnderDragonImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.boss.enderdragon.VirtualEnderDragon {
    private static final EntityDataAccessor<Integer> DATA_PHASE;

    public VirtualEnderDragonImpl() {
        super(VirtualEntityType.ENDER_DRAGON);
    }

    @Override
    public EnderDragonPhase getPhase() {
        return EnderDragonPhase.values()[entityData.get(DATA_PHASE)];
    }

    @Override
    public void setPhase(EnderDragonPhase phase) {
        entityData.set(DATA_PHASE, phase.ordinal());
    }

    static {
        DATA_PHASE = Mappings.findAccessor("EnderDragon", "DATA_PHASE");
    }
}
