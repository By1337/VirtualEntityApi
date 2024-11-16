package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualGuardian extends VirtualMob {
    boolean isMoving();
    void setMoving(boolean flag);
    void setActiveAttackTarget(int entityId);
    int getActiveAttackTarget();
    boolean hasActiveAttackTarget();
}
