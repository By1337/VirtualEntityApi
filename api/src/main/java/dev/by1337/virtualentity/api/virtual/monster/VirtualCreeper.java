package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualCreeper extends VirtualMob {
    boolean isPowered();
    int getSwellDir();
    void setSwellDir(int i);
    void setPowered(boolean powered);
    boolean isIgnited();
    void ignite();
    void setIgnited(boolean ignited);
}
