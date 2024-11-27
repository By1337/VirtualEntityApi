package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.BoatType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualBoatImpl extends VirtualVehicleEntityImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE;
    private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_LEFT;
    private static final EntityDataAccessor<Boolean> DATA_ID_PADDLE_RIGHT;
    private static final EntityDataAccessor<Integer> DATA_ID_BUBBLE_TIME;

    public VirtualBoatImpl() {
        super(VirtualEntityType.BOAT);
    }

    public VirtualBoatImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, BoatType.OAK.getId());
        this.entityData.define(DATA_ID_PADDLE_LEFT, false);
        this.entityData.define(DATA_ID_PADDLE_RIGHT, false);
        this.entityData.define(DATA_ID_BUBBLE_TIME, 0);
    }

    /**
     * Получает тип лодки.
     *
     * @return тип лодки как {@link BoatType}.
     */
    @Override
    public BoatType getBoatType() {
        return BoatType.values()[this.entityData.get(DATA_ID_TYPE)];
    }

    /**
     * Устанавливает тип лодки.
     *
     * @param boatType тип лодки, который нужно установить.
     */
    @Override
    public void setBoatType(BoatType boatType) {
        this.entityData.set(DATA_ID_TYPE, boatType.getId());
    }

    /**
     * Проверяет, используется ли левое весло.
     *
     * @return {@code true}, если левое весло активно, иначе {@code false}.
     */
    @Override
    public boolean isPaddleLeftActive() {
        return this.entityData.get(DATA_ID_PADDLE_LEFT);
    }

    /**
     * Устанавливает состояние левого весла.
     *
     * @param active {@code true}, если левое весло активно, иначе {@code false}.
     */
    @Override
    public void setPaddleLeftActive(boolean active) {
        this.entityData.set(DATA_ID_PADDLE_LEFT, active);
    }

    /**
     * Проверяет, используется ли правое весло.
     *
     * @return {@code true}, если правое весло активно, иначе {@code false}.
     */
    @Override
    public boolean isPaddleRightActive() {
        return this.entityData.get(DATA_ID_PADDLE_RIGHT);
    }

    /**
     * Устанавливает состояние правого весла.
     *
     * @param active {@code true}, если правое весло активно, иначе {@code false}.
     */
    @Override
    public void setPaddleRightActive(boolean active) {
        this.entityData.set(DATA_ID_PADDLE_RIGHT, active);
    }

    /**
     * Получает текущее время пузырьков, связанное с лодкой.
     * Это время может быть использовано для управления эффектами пузырьков или частиц, возникающих рядом с лодкой.
     *
     * @return время пузырьков.
     */
    @Override
    public int getBubbleTime() {
        return this.entityData.get(DATA_ID_BUBBLE_TIME);
    }

    /**
     * Устанавливает время пузырьков для лодки.
     *
     * @param bubbleTime время пузырьков для установки.
     */
    @Override
    public void setBubbleTime(int bubbleTime) {
        this.entityData.set(DATA_ID_BUBBLE_TIME, bubbleTime);
    }

    static {
        DATA_ID_TYPE = Mappings.findAccessor("Boat", "DATA_ID_TYPE");
        DATA_ID_PADDLE_LEFT = Mappings.findAccessor("Boat", "DATA_ID_PADDLE_LEFT");
        DATA_ID_PADDLE_RIGHT = Mappings.findAccessor("Boat", "DATA_ID_PADDLE_RIGHT");
        DATA_ID_BUBBLE_TIME = Mappings.findAccessor("Boat", "DATA_ID_BUBBLE_TIME");
    }
}
