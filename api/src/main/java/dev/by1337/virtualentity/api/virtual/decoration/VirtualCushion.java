package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualCushion extends VirtualEntity {
    void setColor(DyeColor color);

    DyeColor color();

    static VirtualCushion create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CUSHION, VirtualCushion.class);
    }
}
