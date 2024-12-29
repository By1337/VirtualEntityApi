package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualPig extends VirtualAgeableMob {
    int getBoostTime();

    void setBoostTime(int boostTime);

    boolean isSaddle();

    void setSaddle(boolean flag);

    static VirtualPig create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PIG, VirtualPig.class);
    }
}
