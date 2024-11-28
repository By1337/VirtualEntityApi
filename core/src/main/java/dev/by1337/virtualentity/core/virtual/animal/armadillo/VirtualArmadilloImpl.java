package dev.by1337.virtualentity.core.virtual.animal.armadillo;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ArmadilloState;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

@SinceMinecraftVersion("1.20.6")
public class VirtualArmadilloImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.armadillo.VirtualArmadillo {
    private static final EntityDataAccessor<ArmadilloState> ARMADILLO_STATE;

    public VirtualArmadilloImpl() {
        super(VirtualEntityType.ARMADILLO);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(ARMADILLO_STATE, ArmadilloState.IDLE);
    }

    @Override
    public void setState(ArmadilloState state) {
        entityData.set(ARMADILLO_STATE, state);
    }

    @Override
    public ArmadilloState getState() {
        return entityData.get(ARMADILLO_STATE);
    }

    static {
        ARMADILLO_STATE = Mappings.findAccessor("Armadillo", "ARMADILLO_STATE");
    }
}
