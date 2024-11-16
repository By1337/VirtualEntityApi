package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualMinecartImpl extends VirtualAbstractMinecartImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualMinecart {

    public VirtualMinecartImpl() {
        super(VirtualEntityType.MINECART);
    }
}
