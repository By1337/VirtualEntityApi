package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualThrownTridentImpl extends VirtualAbstractArrowImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualThrownTrident {
    private static final EntityDataAccessor<Byte> ID_LOYALTY;
    private static final EntityDataAccessor<Boolean> ID_FOIL;

    public VirtualThrownTridentImpl() {
        super(VirtualEntityType.TRIDENT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_LOYALTY, (byte)0);
        this.entityData.define(ID_FOIL, false);
    }

    /**
     * Получает текущий уровень зачарования "Лояльность" для брошенного трезубца.
     * Уровень лояльности определяет, насколько далеко трезубец будет возвращаться к бросившему его игроку.
     *
     * @return уровень зачарования "Лояльность" (от 0 до 3).
     */
    @Override
    public byte getLoyalty() {
        return this.entityData.get(ID_LOYALTY);
    }

    /**
     * Устанавливает уровень зачарования "Лояльность" для брошенного трезубца.
     *
     * @param loyalty уровень зачарования "Лояльность" (от 0 до 3).
     */
    @Override
    public void setLoyalty(byte loyalty) {
        this.entityData.set(ID_LOYALTY, loyalty);
    }

    /**
     * Проверяет, имеет ли трезубец эффект фолиа.
     * Фольга - это эффект, при котором трезубец будет лететь более быстро.
     *
     * @return {@code true}, если эффект фолиа активирован, иначе {@code false}.
     */
    @Override
    public boolean hasFoil() {
        return this.entityData.get(ID_FOIL);
    }

    /**
     * Устанавливает эффект фолиа для брошенного трезубца.
     *
     * @param foil {@code true}, если эффект фолиа должен быть активирован, иначе {@code false}.
     */
    @Override
    public void setFoil(boolean foil) {
        this.entityData.set(ID_FOIL, foil);
    }

    static {
        ID_LOYALTY = Mappings.findAccessor("ThrownTrident", "ID_LOYALTY");
        ID_FOIL = Mappings.findAccessor("ThrownTrident", "ID_FOIL");
    }
}
