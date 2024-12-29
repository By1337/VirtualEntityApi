package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualBat extends VirtualMob {
    boolean isResting();

    void setResting(boolean flag);

    static VirtualBat create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BAT, VirtualBat.class);
    }
}
