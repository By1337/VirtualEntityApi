package dev.by1337.virtualentity.core.virtual.monster.piglin;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualPiglinImpl extends VirtualAbstractPiglinImpl implements dev.by1337.virtualentity.api.virtual.monster.piglin.VirtualPiglin {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING_CROSSBOW;
    private static final EntityDataAccessor<Boolean> DATA_IS_DANCING;

    public VirtualPiglinImpl() {
        super(VirtualEntityType.PIGLIN);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
        this.entityData.define(DATA_IS_CHARGING_CROSSBOW, false);
        this.entityData.define(DATA_IS_DANCING, false);
    }

    /**
     * Получает статус того, является ли пиглин малышом.
     *
     * @return {@code true}, если пиглин является малышом, {@code false} в противном случае.
     */
    @Override
    public boolean isBaby() {
        return this.entityData.get(DATA_BABY_ID);
    }

    /**
     * Устанавливает статус того, является ли пиглин малышом.
     *
     * @param baby {@code true}, если пиглин должен быть малышом, {@code false} в противном случае.
     */
    @Override
    public void setBaby(boolean baby) {
        this.entityData.set(DATA_BABY_ID, baby);
    }

    /**
     * Проверяет, заряжает ли пиглин арбалет.
     *
     * @return {@code true}, если пиглин заряжает арбалет, {@code false} в противном случае.
     */
    @Override
    public boolean isChargingCrossbow() {
        return this.entityData.get(DATA_IS_CHARGING_CROSSBOW);
    }

    /**
     * Устанавливает состояние зарядки арбалета пиглином.
     *
     * @param charging {@code true}, если пиглин должен быть в процессе зарядки арбалета, {@code false} в противном случае.
     */
    @Override
    public void setChargingCrossbow(boolean charging) {
        this.entityData.set(DATA_IS_CHARGING_CROSSBOW, charging);
    }

    /**
     * Проверяет, танцует ли пиглин.
     *
     * @return {@code true}, если пиглин танцует, {@code false} в противном случае.
     */
    @Override
    public boolean isDancing() {
        return this.entityData.get(DATA_IS_DANCING);
    }

    /**
     * Устанавливает состояние танцевания пиглина.
     *
     * @param dancing {@code true}, если пиглин должен танцевать, {@code false} в противном случае.
     */
    @Override
    public void setDancing(boolean dancing) {
        this.entityData.set(DATA_IS_DANCING, dancing);
    }

    static {
        DATA_BABY_ID = Mappings.findAccessor("Piglin", "DATA_BABY_ID");
        DATA_IS_CHARGING_CROSSBOW = Mappings.findAccessor("Piglin", "DATA_IS_CHARGING_CROSSBOW");
        DATA_IS_DANCING = Mappings.findAccessor("Piglin", "DATA_IS_DANCING");
    }
}
