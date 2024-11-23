package dev.by1337.virtualentity.api.virtual.animal.goat;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

@SinceMinecraftVersion("1.17.1")
public interface VirtualGoat extends VirtualAgeableMob {
    boolean isScreamingGoat();

    void setScreamingGoat(boolean flag);

    @SinceMinecraftVersion("1.19.4")
    void setHasLeftHorn(boolean flag);

    @SinceMinecraftVersion("1.19.4")
    boolean hasLeftHorn();

    @SinceMinecraftVersion("1.19.4")
    void setHasRightHorn(boolean flag);

    @SinceMinecraftVersion("1.19.4")
    boolean hasRightHorn();
}
