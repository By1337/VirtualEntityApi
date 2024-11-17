package dev.by1337.virtualentity.api.virtual.npc;

import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualAbstractVillager extends VirtualAgeableMob {
    int getUnhappyCounter();

    void setUnhappyCounter(int counter);
}
