package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualZombifiedPiglin extends VirtualZombie {

    static VirtualZombifiedPiglin create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ZOMBIFIED_PIGLIN, VirtualZombifiedPiglin.class);
    }
}
