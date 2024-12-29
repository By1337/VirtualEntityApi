package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualCow extends VirtualAgeableMob {
    static VirtualCow create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.COW, VirtualCow.class);
    }
}
