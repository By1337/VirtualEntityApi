package dev.by1337.virtualentity.api.virtual.npc;

import dev.by1337.virtualentity.api.entity.npc.VillagerData;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualVillager extends VirtualAbstractVillager {
    VillagerData getVillagerData();

    void setVillagerData(VillagerData villagerData);
}
