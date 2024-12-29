package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualBee extends VirtualAgeableMob {
    int getRemainingPersistentAngerTime();

    void setRemainingPersistentAngerTime(int time);

    boolean hasStung();

    void setHasStung(boolean flag);

    boolean isRolling();

    void setRolling(boolean flag);

    static VirtualBee create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BEE, VirtualBee.class);
    }
}
