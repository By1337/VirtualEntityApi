package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSnowGolem extends VirtualMob {
    boolean hasPumpkin();

    void setPumpkin(boolean flag);
}
