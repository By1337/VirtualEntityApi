package dev.by1337.virtualentity.api.virtual.monster.piglin;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualPiglin extends VirtualAbstractPiglin {
    boolean isBaby();

    void setBaby(boolean baby);

    boolean isChargingCrossbow();

    void setChargingCrossbow(boolean charging);

    boolean isDancing();

    void setDancing(boolean dancing);

    static VirtualPiglin create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PIGLIN, VirtualPiglin.class);
    }
}
