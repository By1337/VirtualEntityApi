package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualMagmaCube extends VirtualSlime {

    static VirtualMagmaCube create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.MAGMA_CUBE, VirtualMagmaCube.class);
    }
}
