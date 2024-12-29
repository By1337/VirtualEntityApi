package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMule extends VirtualAbstractChestedHorse {
    static VirtualMule create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.MULE, VirtualMule.class);
    }
}
