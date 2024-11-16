package dev.by1337.virtualentity.core.virtual.monster.hoglin;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

public class VirtualHoglinImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.monster.hoglin.VirtualHoglin {
    private static final EntityDataAccessor<Boolean> DATA_IMMUNE_TO_ZOMBIFICATION;

    public VirtualHoglinImpl() {
        super(VirtualEntityType.HOGLIN);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IMMUNE_TO_ZOMBIFICATION, false);
    }

    /**
     * Получает состояние иммунитета хоглина к зомбированию.
     *
     * @return {@code true}, если хоглин защищён от превращения в зомби, {@code false} в противном случае.
     */
    @Override
    public boolean isImmuneToZombification() {
        return this.entityData.get(DATA_IMMUNE_TO_ZOMBIFICATION);
    }

    /**
     * Устанавливает состояние иммунитета хоглина к зомбированию.
     *
     * @param immune {@code true}, если хоглин должен быть защищён от превращения в зомби, {@code false} в противном случае.
     */
    @Override
    public void setImmuneToZombification(boolean immune) {
        this.entityData.set(DATA_IMMUNE_TO_ZOMBIFICATION, immune);
    }

    static {
        DATA_IMMUNE_TO_ZOMBIFICATION = Mappings.findAccessor("Hoglin", "DATA_IMMUNE_TO_ZOMBIFICATION");
    }
}
