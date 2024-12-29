package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualRabbit extends VirtualAgeableMob {
    int getRabbitType();

    // 0 - 5
    // 99 = KILLER_BUNNY
    void setRabbitType(int type);

    static VirtualRabbit create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.RABBIT, VirtualRabbit.class);
    }
}
