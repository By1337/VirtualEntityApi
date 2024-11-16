package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualBee extends VirtualAgableMob {
    int getRemainingPersistentAngerTime();
    void setRemainingPersistentAngerTime(int time);
    boolean hasStung();
    void setHasStung(boolean flag);
    boolean isRolling();
    void setRolling(boolean flag);
}
