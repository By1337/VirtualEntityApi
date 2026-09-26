package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

@SinceMinecraftVersion("26.3")
public interface VirtualCushion extends VirtualEntity {
    DyeColor getColor();

    void setColor(DyeColor color);

    static VirtualCushion create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CUSHION, VirtualCushion.class);
    }
}
