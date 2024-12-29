package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;

public interface VirtualPainting extends VirtualHangingEntity {
    void setMotive(PaintingMotive motive);

    PaintingMotive motive();

    static VirtualPainting create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PAINTING, VirtualPainting.class);
    }
}
