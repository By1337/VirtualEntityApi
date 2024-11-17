package dev.by1337.virtualentity.api.virtual.monster.hoglin;

import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualHoglin extends VirtualAgeableMob {
    boolean isImmuneToZombification();

    void setImmuneToZombification(boolean immune);
}
