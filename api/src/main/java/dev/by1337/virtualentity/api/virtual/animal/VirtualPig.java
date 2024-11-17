package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualPig extends VirtualAgeableMob {
    int getBoostTime();

    void setBoostTime(int boostTime);

    boolean isSaddle();

    void setSaddle(boolean flag);
}
