package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualPhantom extends VirtualMob {
    // 0 - 64
    void setPhantomSize(int size);

    int getPhantomSize();
}
