package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.raid.VirtualRaider;

public interface VirtualPillager extends VirtualRaider {
    void setChargingCrossbow(boolean flag);

    boolean isChargingCrossbow();
}
