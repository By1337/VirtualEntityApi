package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMinecartSpawner extends VirtualAbstractMinecart {

    static VirtualMinecartSpawner create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SPAWNER_MINECART, VirtualMinecartSpawner.class);
    }
}
