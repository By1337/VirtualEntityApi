package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualEvokerFangs extends VirtualEntity {

    static VirtualEvokerFangs create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.EVOKER_FANGS, VirtualEvokerFangs.class);
    }
}
