package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

@SinceMinecraftVersion("1.20.6")
public interface VirtualBogged extends VirtualMob {
    boolean isSheared();

    void setSheared(boolean sheared);
}
