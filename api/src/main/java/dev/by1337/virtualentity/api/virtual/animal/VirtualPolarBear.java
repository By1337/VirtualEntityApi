package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualPolarBear extends VirtualAgableMob {
    boolean isStanding();

    void setStanding(boolean flag);
}
