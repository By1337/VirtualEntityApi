package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMinecartHopper extends VirtualAbstractMinecart {

    static VirtualMinecartHopper create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.HOPPER_MINECART, VirtualMinecartHopper.class);
    }
}
