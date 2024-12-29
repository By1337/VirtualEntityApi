package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualZoglin extends VirtualMob {
    void setBaby(boolean param0);

    boolean isBaby();

    static VirtualZoglin create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ZOGLIN, VirtualZoglin.class);
    }
}
