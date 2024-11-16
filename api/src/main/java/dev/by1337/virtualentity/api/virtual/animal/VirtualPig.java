package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualPig extends VirtualAgableMob {
    int getBoostTime();

    void setBoostTime(int boostTime);

    boolean isSaddle();

    void setSaddle(boolean flag);
}
