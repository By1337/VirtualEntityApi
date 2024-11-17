package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.by1337.blib.util.Direction;

public class VirtualPaintingImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting {

    private PaintingMotive motive = PaintingMotive.ALBAN;
    private Direction direction = Direction.EAST;

    public VirtualPaintingImpl() {
        super(VirtualEntityType.PAINTING);
    }

    @Override
    public PaintingMotive motive() {
        return motive;
    }

    @Override
    public void setMotive(PaintingMotive motive) {
        this.motive = motive;
    }

    @Override
    public Direction direction() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
