package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualZombieHorse extends VirtualAbstractHorse {

    static VirtualZombieHorse create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ZOMBIE_HORSE, VirtualZombieHorse.class);
    }
}
