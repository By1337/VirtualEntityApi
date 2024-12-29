package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualStrider extends VirtualAgeableMob {
    int getBoostTime();

    void setBoostTime(int boostTime);

    boolean isSuffocating();

    void setSuffocating(boolean suffocating);

    boolean hasSaddle();

    void setSaddle(boolean saddle);

    static VirtualStrider create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.STRIDER, VirtualStrider.class);
    }
}
