package dev.by1337.virtualentity.api.virtual.projectile.windcharge;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

@SinceMinecraftVersion("1.20.6")
public interface VirtualBreezeWindCharge extends VirtualEntity {

    static VirtualBreezeWindCharge create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BREEZE_WIND_CHARGE, VirtualBreezeWindCharge.class);
    }
}
