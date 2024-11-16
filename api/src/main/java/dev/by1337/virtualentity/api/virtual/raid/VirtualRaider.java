package dev.by1337.virtualentity.api.virtual.raid;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualRaider extends VirtualMob {
    void setCelebrating(boolean flag);

    boolean isCelebrating();
}
