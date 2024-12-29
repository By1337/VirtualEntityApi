package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualBlaze extends VirtualMob {
    boolean isCharged();

    void setCharged(boolean flag);

    static VirtualBlaze create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BLAZE, VirtualBlaze.class);
    }
}
