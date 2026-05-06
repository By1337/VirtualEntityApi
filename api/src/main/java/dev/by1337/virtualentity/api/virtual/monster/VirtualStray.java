package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualStray extends VirtualMob {
    static VirtualStray create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.STRAY, VirtualStray.class);
    }
}
