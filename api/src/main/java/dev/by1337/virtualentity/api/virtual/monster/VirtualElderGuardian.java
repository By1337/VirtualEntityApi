package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualElderGuardian extends VirtualGuardian {
    static VirtualElderGuardian create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ELDER_GUARDIAN, VirtualElderGuardian.class);
    }
}
