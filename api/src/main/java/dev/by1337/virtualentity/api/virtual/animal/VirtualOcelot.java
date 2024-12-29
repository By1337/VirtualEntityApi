package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualOcelot extends VirtualAgeableMob {
    boolean isTrusting();

    void setTrusting(boolean flag);

    static VirtualOcelot create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.OCELOT, VirtualOcelot.class);
    }
}
