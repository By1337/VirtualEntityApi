package dev.by1337.virtualentity.api.virtual.animal.armadillo;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ArmadilloState;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

@SinceMinecraftVersion("1.20.6")
public interface VirtualArmadillo extends VirtualAgeableMob {
    void setState(ArmadilloState state);

    ArmadilloState getState();

    static VirtualArmadillo create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ARMADILLO, VirtualArmadillo.class);
    }
}
