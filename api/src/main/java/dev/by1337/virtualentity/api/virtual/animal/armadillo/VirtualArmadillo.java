package dev.by1337.virtualentity.api.virtual.animal.armadillo;

import dev.by1337.virtualentity.api.entity.ArmadilloState;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualArmadillo extends VirtualAgeableMob {
    void setState(ArmadilloState state);

    ArmadilloState getState();
}
