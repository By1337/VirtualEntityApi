package dev.by1337.virtualentity.core.virtual.projectile.windcharge;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.projectile.windcharge.VirtualWindCharge;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualWindChargeImpl extends VirtualEntityImpl implements VirtualWindCharge {

    public VirtualWindChargeImpl() {
        super(VirtualEntityType.WIND_CHARGE);
    }
}
