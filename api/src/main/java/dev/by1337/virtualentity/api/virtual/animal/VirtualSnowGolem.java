package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSnowGolem extends VirtualMob {
    boolean hasPumpkin();

    void setPumpkin(boolean flag);

    static VirtualSnowGolem create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SNOW_GOLEM, VirtualSnowGolem.class);
    }
}
