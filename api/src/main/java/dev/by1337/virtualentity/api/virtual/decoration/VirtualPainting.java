package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;
import org.by1337.blib.util.Direction;

public interface VirtualPainting extends VirtualHangingEntity {
    void setMotive(PaintingMotive motive);

    PaintingMotive motive();
    @RemovedInMinecraftVersion("1.19.4")
    @Deprecated
    Direction direction();
}
