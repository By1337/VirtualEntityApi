package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualVex extends VirtualMob {
    boolean isCharging();

    void setIsCharging(boolean flag);
}
