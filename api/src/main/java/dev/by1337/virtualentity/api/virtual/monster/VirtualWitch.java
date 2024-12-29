package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.raid.VirtualRaider;

public interface VirtualWitch extends VirtualRaider {
    void setUsingItem(boolean flag);

    boolean isDrinkingPotion();

    static VirtualWitch create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.WITCH, VirtualWitch.class);
    }
}
