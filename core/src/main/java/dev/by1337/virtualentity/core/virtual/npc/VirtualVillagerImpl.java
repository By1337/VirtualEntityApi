package dev.by1337.virtualentity.core.virtual.npc;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;
import dev.by1337.virtualentity.api.entity.npc.VillagerProfession;
import dev.by1337.virtualentity.api.entity.npc.VillagerType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualVillagerImpl extends VirtualAbstractVillagerImpl implements dev.by1337.virtualentity.api.virtual.npc.VirtualVillager {
    private static final EntityDataAccessor<VillagerData> DATA_VILLAGER_DATA;

    public VirtualVillagerImpl() {
        super(VirtualEntityType.VILLAGER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VILLAGER_DATA, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
    }

    /**
     * Получает данные о деревенском жителе.
     * Включает тип деревни, профессию и уровень.
     *
     * @return объект {@link VillagerData}, который содержит информацию о деревенском жителе.
     */
    @Override
    public VillagerData getVillagerData() {
        return this.entityData.get(DATA_VILLAGER_DATA);
    }

    /**
     * Устанавливает данные о деревенском жителе.
     *
     * @param villagerData объект {@link VillagerData}, который содержит информацию о деревенском жителе.
     */
    @Override
    public void setVillagerData(VillagerData villagerData) {
        this.entityData.set(DATA_VILLAGER_DATA, villagerData);
    }

    static {
        DATA_VILLAGER_DATA = Mappings.findAccessor("Villager", "DATA_VILLAGER_DATA");
    }
}
