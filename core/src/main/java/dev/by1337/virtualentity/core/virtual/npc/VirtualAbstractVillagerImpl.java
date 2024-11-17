package dev.by1337.virtualentity.core.virtual.npc;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualAbstractVillagerImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.npc.VirtualAbstractVillager {
    private static final EntityDataAccessor<Integer> DATA_UNHAPPY_COUNTER;

    public VirtualAbstractVillagerImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_UNHAPPY_COUNTER, 0);
    }

    /**
     * Получает текущий счётчик несчастья для деревенского жителя.
     *
     * @return текущее значение счётчика несчастья.
     */
    @Override
    public int getUnhappyCounter() {
        return this.entityData.get(DATA_UNHAPPY_COUNTER);
    }

    /**
     * Устанавливает новый счётчик несчастья для деревенского жителя.
     *
     * @param counter новое значение счётчика несчастья.
     */
    @Override
    public void setUnhappyCounter(int counter) {
        this.entityData.set(DATA_UNHAPPY_COUNTER, counter);
    }

    static {
        DATA_UNHAPPY_COUNTER = Mappings.findAccessor("AbstractVillager", "DATA_UNHAPPY_COUNTER");
    }
}
