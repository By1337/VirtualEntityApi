package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualVex extends VirtualMob {
    boolean isCharging();

    void setIsCharging(boolean flag);

    static VirtualVex create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.VEX, VirtualVex.class);
    }
}
