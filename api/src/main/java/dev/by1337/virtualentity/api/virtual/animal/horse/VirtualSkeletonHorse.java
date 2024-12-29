package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualSkeletonHorse extends VirtualAbstractHorse {
    static VirtualSkeletonHorse create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SKELETON_HORSE, VirtualSkeletonHorse.class);
    }
}
