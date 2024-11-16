package dev.by1337.virtualentity.core.virtual.vehicle;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualMinecartChestImpl extends VirtualAbstractMinecartImpl implements dev.by1337.virtualentity.api.virtual.vehicle.VirtualMinecartChest {

    public VirtualMinecartChestImpl() {
        super(VirtualEntityType.CHEST_MINECART);
    }
}
