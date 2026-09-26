package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

/** @deprecated Use {@link VirtualEnderman}. */
@Deprecated
public interface VirtualEnderMan extends VirtualEnderman {
    static VirtualEnderMan create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ENDERMAN, VirtualEnderMan.class);
    }
}
