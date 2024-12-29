package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.raid.VirtualRaider;

public interface VirtualPillager extends VirtualRaider {
    void setChargingCrossbow(boolean flag);

    boolean isChargingCrossbow();

    static VirtualPillager create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PILLAGER, VirtualPillager.class);
    }
}
