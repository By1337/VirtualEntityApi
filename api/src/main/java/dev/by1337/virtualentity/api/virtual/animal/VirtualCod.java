package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualCod extends VirtualAbstractFish {
    static VirtualCod create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.COD, VirtualCod.class);
    }
}
