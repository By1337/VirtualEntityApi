package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualBoat extends VirtualAbstractBoat {

    static VirtualBoat create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BOAT, VirtualBoat.class);
    }
}
