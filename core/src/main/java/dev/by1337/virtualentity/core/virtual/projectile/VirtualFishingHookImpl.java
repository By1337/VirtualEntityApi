package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualFishingHookImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualFishingHook {
    private static final EntityDataAccessor<Integer> DATA_HOOKED_ENTITY;
    private static final EntityDataAccessor<Boolean> DATA_BITING;

    public VirtualFishingHookImpl() {
        super(VirtualEntityType.FISHING_BOBBER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_HOOKED_ENTITY, 0);
        this.entityData.define(DATA_BITING, false);
    }

    /**
     * Получает текущую сущность, которая была поймана рыболовным крючком.
     *
     * @return ID сущности, пойманной рыболовным крючком (0, если ничего не поймано).
     */
    @Override
    public int getHookedEntity() {
        return this.entityData.get(DATA_HOOKED_ENTITY);
    }

    /**
     * Устанавливает сущность, которая была поймана рыболовным крючком.
     *
     * @param entityId ID сущности, пойманной рыболовным крючком (0, если ничего не поймано).
     */
    @Override
    public void setHookedEntity(int entityId) {
        this.entityData.set(DATA_HOOKED_ENTITY, entityId);
    }

    /**
     * Проверяет, клюёт ли рыба на крючок.
     *
     * @return {@code true}, если рыба клюёт, иначе {@code false}.
     */
    @Override
    public boolean isBiting() {
        return this.entityData.get(DATA_BITING);
    }

    /**
     * Устанавливает, клюёт ли рыба на крючок.
     *
     * @param biting {@code true}, если рыба клюёт, иначе {@code false}.
     */
    @Override
    public void setBiting(boolean biting) {
        this.entityData.set(DATA_BITING, biting);
    }

    static {
        DATA_HOOKED_ENTITY = Mappings.findAccessor("FishingHook", "DATA_HOOKED_ENTITY");
        DATA_BITING = Mappings.findAccessor("FishingHook", "DATA_BITING");
    }
}
