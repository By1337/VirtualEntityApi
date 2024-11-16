package dev.by1337.virtualentity.api.virtual.monster.hoglin;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualHoglin extends VirtualAgableMob {
    boolean isImmuneToZombification();

    void setImmuneToZombification(boolean immune);
}
