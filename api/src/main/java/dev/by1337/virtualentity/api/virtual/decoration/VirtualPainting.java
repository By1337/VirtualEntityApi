package dev.by1337.virtualentity.api.virtual.decoration;

import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.by1337.blib.util.Direction;

public interface VirtualPainting extends VirtualEntity {
    void setMotive(PaintingMotive motive);
    PaintingMotive motive();
    Direction direction();
    void setDirection(Direction direction);
}
