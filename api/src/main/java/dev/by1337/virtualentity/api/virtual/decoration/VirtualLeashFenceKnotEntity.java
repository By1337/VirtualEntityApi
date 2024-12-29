package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualLeashFenceKnotEntity extends VirtualEntity {

    static VirtualLeashFenceKnotEntity create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.LEASH_KNOT, VirtualLeashFenceKnotEntity.class);
    }
}
