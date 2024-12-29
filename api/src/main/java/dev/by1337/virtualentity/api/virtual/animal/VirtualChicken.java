package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualChicken extends VirtualAgeableMob {

    static VirtualChicken create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CHICKEN, VirtualChicken.class);
    }
}
