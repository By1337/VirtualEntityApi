package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.OptionalInt;

public class VirtualFireworkRocketEntityImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualFireworkRocketEntity {
    private static final EntityDataAccessor<ItemStack> DATA_ID_FIREWORKS_ITEM;
    private static final EntityDataAccessor<OptionalInt> DATA_ATTACHED_TO_TARGET;
    private static final EntityDataAccessor<Boolean> DATA_SHOT_AT_ANGLE;

    public VirtualFireworkRocketEntityImpl() {
        super(VirtualEntityType.FIREWORK_ROCKET);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FIREWORKS_ITEM, new ItemStack(Material.AIR));
        this.entityData.define(DATA_ATTACHED_TO_TARGET, OptionalInt.empty());
        this.entityData.define(DATA_SHOT_AT_ANGLE, false);
    }

    /**
     * Получает текущий предмет фейерверка, связанный с ракетой.
     *
     * @return объект {@link ItemStack}, представляющий предмет фейерверка.
     */
    @Override
    public ItemStack getFireworkItem() {
        return this.entityData.get(DATA_ID_FIREWORKS_ITEM);
    }

    /**
     * Устанавливает новый предмет фейерверка для ракеты.
     *
     * @param fireworkItem новый предмет фейерверка.
     */
    @Override
    public void setFireworkItem(ItemStack fireworkItem) {
        this.entityData.set(DATA_ID_FIREWORKS_ITEM, fireworkItem);
    }

    /**
     * Получает информацию о том, прикреплена ли ракета к цели.
     *
     * @return {@link OptionalInt}, который может содержать идентификатор цели, к которой прикреплена ракета.
     */
    @Override
    public OptionalInt getAttachedToTarget() {
        return this.entityData.get(DATA_ATTACHED_TO_TARGET);
    }

    /**
     * Устанавливает информацию о том, прикреплена ли ракета к цели.
     *
     * @param targetId идентификатор цели, к которой ракета будет прикреплена.
     *                 Если ракета не прикреплена, можно передать {@link OptionalInt#empty()}.
     */
    @Override
    public void setAttachedToTarget(OptionalInt targetId) {
        this.entityData.set(DATA_ATTACHED_TO_TARGET, targetId);
    }

    /**
     * Получает информацию о том, была ли ракета выстрелена под углом.
     *
     * @return {@code true}, если ракета была выстрелена под углом, иначе {@code false}.
     */
    @Override
    public boolean isShotAtAngle() {
        return this.entityData.get(DATA_SHOT_AT_ANGLE);
    }

    /**
     * Устанавливает информацию о том, была ли ракета выстрелена под углом.
     *
     * @param shotAtAngle {@code true}, если ракета была выстрелена под углом, иначе {@code false}.
     */
    @Override
    public void setShotAtAngle(boolean shotAtAngle) {
        this.entityData.set(DATA_SHOT_AT_ANGLE, shotAtAngle);
    }

    static {
        DATA_ID_FIREWORKS_ITEM = Mappings.findAccessor("FireworkRocketEntity", "DATA_ID_FIREWORKS_ITEM");
        DATA_ATTACHED_TO_TARGET = Mappings.findAccessor("FireworkRocketEntity", "DATA_ATTACHED_TO_TARGET");
        DATA_SHOT_AT_ANGLE = Mappings.findAccessor("FireworkRocketEntity", "DATA_SHOT_AT_ANGLE");
    }
}
