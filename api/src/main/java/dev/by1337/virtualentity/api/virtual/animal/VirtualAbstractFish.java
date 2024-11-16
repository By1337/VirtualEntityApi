package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualAbstractFish extends VirtualMob {
    boolean isFromBucket();

    void setFromBucket(boolean flag);
}
