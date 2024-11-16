package dev.by1337.virtualentity.core.virtual.monster.piglin;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualAbstractPiglinImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.piglin.VirtualAbstractPiglin {
    private static final EntityDataAccessor<Boolean> DATA_IMMUNE_TO_ZOMBIFICATION;

    public VirtualAbstractPiglinImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_IMMUNE_TO_ZOMBIFICATION, false);
    }

    /**
     * Получает состояние иммунитета к зомбированию для пиглина.
     *
     * @return {@code true}, если пиглин защищён от зомбирования, {@code false} в противном случае.
     */
    @Override
    public boolean isImmuneToZombification() {
        return this.entityData.get(DATA_IMMUNE_TO_ZOMBIFICATION);
    }

    /**
     * Устанавливает состояние иммунитета к зомбированию для пиглина.
     *
     * @param immune {@code true}, если пиглин должен быть защищён от зомбирования, {@code false} в противном случае.
     */
    @Override
    public void setImmuneToZombification(boolean immune) {
        this.entityData.set(DATA_IMMUNE_TO_ZOMBIFICATION, immune);
    }

    static {
        DATA_IMMUNE_TO_ZOMBIFICATION = Mappings.findAccessor("AbstractPiglin", "DATA_IMMUNE_TO_ZOMBIFICATION");
    }
}
