package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;
import dev.by1337.virtualentity.api.entity.npc.VillagerProfession;
import dev.by1337.virtualentity.api.entity.npc.VillagerType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualZombieVillagerImpl extends VirtualZombieImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualZombieVillager {
    private static final EntityDataAccessor<Boolean> DATA_CONVERTING_ID;
    private static final EntityDataAccessor<VillagerData> DATA_VILLAGER_DATA;

    public VirtualZombieVillagerImpl() {
        super(VirtualEntityType.ZOMBIE_VILLAGER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CONVERTING_ID, false);
        this.entityData.define(DATA_VILLAGER_DATA, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
    }

    /**
     * Получает статус превращения зомби-деревенского жителя.
     * @return {@code true}, если зомби превращается в деревенского жителя, {@code false} в противном случае.
     */
    @Override
    public boolean isConverting() {
        return this.entityData.get(DATA_CONVERTING_ID);
    }

    /**
     * Устанавливает статус превращения зомби-деревенского жителя.
     * @param converting {@code true}, если зомби должен превращаться в деревенского жителя, {@code false} в противном случае.
     */
    @Override
    public void setConverting(boolean converting) {
        this.entityData.set(DATA_CONVERTING_ID, converting);
    }

    /**
     * Получает данные о деревенском жителе, к которому может быть превращен зомби.
     * @return объект {@link VillagerData}, содержащий информацию о типе деревни, профессии и уровне.
     */
    @Override
    public VillagerData getVillagerData() {
        return this.entityData.get(DATA_VILLAGER_DATA);
    }

    /**
     * Устанавливает данные о деревенском жителе.
     * @param villagerData объект {@link VillagerData}, содержащий информацию о типе деревни, профессии и уровне.
     */
    @Override
    public void setVillagerData(VillagerData villagerData) {
        this.entityData.set(DATA_VILLAGER_DATA, villagerData);
    }

    static {
        DATA_CONVERTING_ID = Mappings.findAccessor("ZombieVillager", "DATA_CONVERTING_ID");
        DATA_VILLAGER_DATA = Mappings.findAccessor("ZombieVillager", "DATA_VILLAGER_DATA");
    }
}
