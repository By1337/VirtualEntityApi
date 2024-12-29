package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMinecartTNT extends VirtualAbstractMinecart {

    static VirtualMinecartTNT create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TNT_MINECART, VirtualMinecartTNT.class);
    }
}
