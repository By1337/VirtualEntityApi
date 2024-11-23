package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualWarden extends VirtualMob {
    int getClientAngerLevel();

    void setClientAngerLevel(int anger);
}
