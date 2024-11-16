package dev.by1337.virtualentity.api.virtual;

public interface VirtualGuardian extends VirtualMob{
    boolean isMoving();
    void setMoving(boolean flag);
    void setActiveAttackTarget(int entityId);
    int getActiveAttackTarget();
    boolean hasActiveAttackTarget();
}
