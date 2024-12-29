package dev.by1337.virtualentity.api.virtual.npc;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;

public interface VirtualVillager extends VirtualAbstractVillager {
    VillagerData getVillagerData();

    void setVillagerData(VillagerData villagerData);

    static VirtualVillager create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.VILLAGER, VirtualVillager.class);
    }
}
