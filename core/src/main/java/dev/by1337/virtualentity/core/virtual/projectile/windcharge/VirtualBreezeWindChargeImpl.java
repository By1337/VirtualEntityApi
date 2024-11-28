package dev.by1337.virtualentity.core.virtual.projectile.windcharge;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

@SinceMinecraftVersion("1.20.6")
public class VirtualBreezeWindChargeImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.windcharge.VirtualBreezeWindCharge {

    public VirtualBreezeWindChargeImpl() {
        super(VirtualEntityType.BREEZE_WIND_CHARGE);
    }
}