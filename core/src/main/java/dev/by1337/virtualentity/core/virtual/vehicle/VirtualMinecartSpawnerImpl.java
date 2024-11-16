package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualMinecartSpawnerImpl extends VirtualAbstractMinecartImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualMinecartSpawner {

    public VirtualMinecartSpawnerImpl() {
        super(VirtualEntityType.SPAWNER_MINECART);
    }
}
