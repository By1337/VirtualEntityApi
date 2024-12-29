package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualPhantom extends VirtualMob {
    // 0 - 64
    void setPhantomSize(int size);

    int getPhantomSize();

    static VirtualPhantom create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PHANTOM, VirtualPhantom.class);
    }
}
