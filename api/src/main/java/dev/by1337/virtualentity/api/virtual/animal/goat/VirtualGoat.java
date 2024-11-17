package dev.by1337.virtualentity.api.virtual.animal.goat;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

@SinceMinecraftVersion("1.17.1")
public interface VirtualGoat extends VirtualAgeableMob {
    boolean isScreamingGoat();

    void setScreamingGoat(boolean flag);
}
