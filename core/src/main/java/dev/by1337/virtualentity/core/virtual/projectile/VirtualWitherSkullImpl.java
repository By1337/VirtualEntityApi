package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualWitherSkullImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualWitherSkull {
    private static final EntityDataAccessor<Boolean> DATA_DANGEROUS;

    public VirtualWitherSkullImpl() {
        super(VirtualEntityType.WITHER_SKULL);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_DANGEROUS, false);
    }


    /**
     * Проверяет, является ли голова Визера опасной.
     * Опасная голова Визера может нанести урон при попадании в цель.
     *
     * @return {@code true}, если голова опасная, иначе {@code false}.
     */
    @Override
    public boolean isDangerous() {
        return this.entityData.get(DATA_DANGEROUS);
    }

    /**
     * Устанавливает, является ли голова Визера опасной.
     *
     * @param dangerous {@code true}, если голова должна быть опасной, иначе {@code false}.
     */
    @Override
    public void setDangerous(boolean dangerous) {
        this.entityData.set(DATA_DANGEROUS, dangerous);
    }

    static {
        DATA_DANGEROUS = Mappings.findAccessor("WitherSkull", "DATA_DANGEROUS");
    }
}
