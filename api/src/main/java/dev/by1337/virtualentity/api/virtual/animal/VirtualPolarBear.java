package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualPolarBear extends VirtualAgeableMob {
    boolean isStanding();

    void setStanding(boolean flag);
}
