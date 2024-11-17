package dev.by1337.virtualentity.api.virtual.animal.goat;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

@SinceMinecraftVersion("1.17.1")
public interface VirtualGoat extends VirtualAgableMob {
    boolean isScreamingGoat();

    void setScreamingGoat(boolean flag);
}
