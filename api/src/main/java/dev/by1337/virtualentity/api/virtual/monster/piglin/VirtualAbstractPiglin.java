package dev.by1337.virtualentity.api.virtual.monster.piglin;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualAbstractPiglin extends VirtualMob {
    boolean isImmuneToZombification();

    void setImmuneToZombification(boolean immune);
}
