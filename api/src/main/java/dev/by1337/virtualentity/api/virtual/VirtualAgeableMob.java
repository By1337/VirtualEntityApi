package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

public interface VirtualAgeableMob extends VirtualMob {
    boolean isBaby();

    void setBaby(boolean flag);

    @SinceMinecraftVersion("26.1")
    boolean isAgeLocked();

    @SinceMinecraftVersion("26.1")
    void setAgeLocked(boolean value);
}
