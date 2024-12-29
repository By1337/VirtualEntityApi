package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualHusk extends VirtualZombie {

    static VirtualHusk create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.HUSK, VirtualHusk.class);
    }
}
