package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMinecart extends VirtualAbstractMinecart {

    static VirtualMinecart create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.MINECART, VirtualMinecart.class);
    }
}
