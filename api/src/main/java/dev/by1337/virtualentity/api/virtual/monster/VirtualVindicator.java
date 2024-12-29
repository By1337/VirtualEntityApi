package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.raid.VirtualRaider;

public interface VirtualVindicator extends VirtualRaider {
    static VirtualVindicator create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.VINDICATOR, VirtualVindicator.class);
    }
}
