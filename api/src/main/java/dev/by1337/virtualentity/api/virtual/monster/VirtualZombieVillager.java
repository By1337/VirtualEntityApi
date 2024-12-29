package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;

public interface VirtualZombieVillager extends VirtualZombie {
    boolean isConverting();

    void setConverting(boolean converting);

    VillagerData getVillagerData();

    void setVillagerData(VillagerData villagerData);

    static VirtualZombieVillager create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ZOMBIE_VILLAGER, VirtualZombieVillager.class);
    }
}
