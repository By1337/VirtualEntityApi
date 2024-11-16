package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.entity.npc.VillagerData;

public interface VirtualZombieVillager extends VirtualZombie {
    boolean isConverting();

    void setConverting(boolean converting);

    VillagerData getVillagerData();

    void setVillagerData(VillagerData villagerData);
}
