package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualThrownTrident extends VirtualAbstractArrow {
    byte getLoyalty();

    void setLoyalty(byte loyalty);

    boolean hasFoil();

    void setFoil(boolean foil);

    static VirtualThrownTrident create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TRIDENT, VirtualThrownTrident.class);
    }
}
