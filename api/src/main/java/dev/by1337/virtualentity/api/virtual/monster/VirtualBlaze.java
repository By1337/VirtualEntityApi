package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualBlaze extends VirtualMob {
    boolean isCharged();

    void setCharged(boolean flag);
}
