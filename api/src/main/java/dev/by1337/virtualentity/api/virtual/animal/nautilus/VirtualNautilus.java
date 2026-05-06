package dev.by1337.virtualentity.api.virtual.animal.nautilus;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualNautilus extends VirtualAbstractNautilus {
    static VirtualNautilus create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.NAUTILUS, VirtualNautilus.class);
    }
}
