package dev.by1337.virtualentity.api.virtual.projectile.windcharge;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

@SinceMinecraftVersion("1.20.4")
public interface VirtualWindCharge extends VirtualEntity {

    static VirtualWindCharge create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.WIND_CHARGE, VirtualWindCharge.class);
    }
}
