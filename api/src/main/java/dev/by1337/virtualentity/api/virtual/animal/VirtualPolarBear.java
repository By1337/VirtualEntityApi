package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualPolarBear extends VirtualAgeableMob {
    boolean isStanding();

    void setStanding(boolean flag);

    static VirtualPolarBear create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.POLAR_BEAR, VirtualPolarBear.class);
    }
}
