package dev.by1337.virtualentity.api.virtual.monster.creaking;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

@SinceMinecraftVersion("1.21.3")
public interface VirtualCreaking extends VirtualMob {
    void setIsActive(boolean isActive);

    boolean isIsActive();

    void setCanMove(boolean canMove);

    boolean isCanMove();
}
