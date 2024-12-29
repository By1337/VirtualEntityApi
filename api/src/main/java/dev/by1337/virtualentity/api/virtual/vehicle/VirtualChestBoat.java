package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualChestBoat extends VirtualAbstractBoat {

    static VirtualChestBoat create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CHEST_BOAT, VirtualChestBoat.class);
    }
}
