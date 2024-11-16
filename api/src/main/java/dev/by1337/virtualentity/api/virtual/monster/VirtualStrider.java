package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualStrider extends VirtualAgableMob {
    int getBoostTime();

    void setBoostTime(int boostTime);

    boolean isSuffocating();

    void setSuffocating(boolean suffocating);

    boolean hasSaddle();

    void setSaddle(boolean saddle);
}
