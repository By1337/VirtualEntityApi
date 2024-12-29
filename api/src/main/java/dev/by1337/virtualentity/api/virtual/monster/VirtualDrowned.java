package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualDrowned extends VirtualZombie {

    static VirtualDrowned create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.DROWNED, VirtualDrowned.class);
    }
}
