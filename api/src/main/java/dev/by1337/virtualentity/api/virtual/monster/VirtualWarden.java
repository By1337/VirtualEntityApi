package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualWarden extends VirtualMob {
    int getClientAngerLevel();

    void setClientAngerLevel(int anger);

    static VirtualWarden create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.WARDEN, VirtualWarden.class);
    }
}
