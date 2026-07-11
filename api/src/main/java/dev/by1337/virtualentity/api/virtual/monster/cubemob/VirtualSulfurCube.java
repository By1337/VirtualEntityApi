package dev.by1337.virtualentity.api.virtual.monster.cubemob;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

@SinceMinecraftVersion("26.2")
public interface VirtualSulfurCube extends VirtualAbstractCubeMob {
    int getMaxFuse();

    void setMaxFuse(int fuse);

    boolean isFromBucket();

    void setFromBucket(boolean flag);
}
