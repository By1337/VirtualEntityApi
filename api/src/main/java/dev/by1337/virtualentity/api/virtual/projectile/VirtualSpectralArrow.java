package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualSpectralArrow extends VirtualAbstractArrow {

    static VirtualSpectralArrow create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SPECTRAL_ARROW, VirtualSpectralArrow.class);
    }
}
