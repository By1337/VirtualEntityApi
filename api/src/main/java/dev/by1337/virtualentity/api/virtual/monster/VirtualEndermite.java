package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualEndermite extends VirtualMob {

    static VirtualEndermite create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ENDERMITE, VirtualEndermite.class);
    }
}
