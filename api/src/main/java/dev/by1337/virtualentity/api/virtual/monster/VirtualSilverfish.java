package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSilverfish extends VirtualMob {

    static VirtualSilverfish create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SILVERFISH, VirtualSilverfish.class);
    }
}
