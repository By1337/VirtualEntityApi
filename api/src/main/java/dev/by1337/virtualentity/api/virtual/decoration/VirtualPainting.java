package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;

public interface VirtualPainting extends VirtualHangingEntity {
    void setMotive(PaintingMotive motive);

    PaintingMotive motive();
}
