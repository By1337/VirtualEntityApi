package dev.by1337.virtualentity.api.virtual.npc;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualAbstractVillager extends VirtualAgableMob {
    int getUnhappyCounter();

    void setUnhappyCounter(int counter);
}
