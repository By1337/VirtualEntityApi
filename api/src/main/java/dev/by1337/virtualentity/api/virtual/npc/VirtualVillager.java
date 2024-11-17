package dev.by1337.virtualentity.api.virtual.npc;

import dev.by1337.virtualentity.api.entity.npc.VillagerData;

public interface VirtualVillager extends VirtualAbstractVillager {
    VillagerData getVillagerData();

    void setVillagerData(VillagerData villagerData);
}
