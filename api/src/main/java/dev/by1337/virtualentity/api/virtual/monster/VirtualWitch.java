package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.raid.VirtualRaider;

public interface VirtualWitch extends VirtualRaider {
    void setUsingItem(boolean flag);

    boolean isDrinkingPotion();
}
