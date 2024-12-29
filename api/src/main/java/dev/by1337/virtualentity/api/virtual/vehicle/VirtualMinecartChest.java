package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMinecartChest extends VirtualAbstractMinecart {

    static VirtualMinecartChest create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CHEST_MINECART, VirtualMinecartChest.class);
    }
}
