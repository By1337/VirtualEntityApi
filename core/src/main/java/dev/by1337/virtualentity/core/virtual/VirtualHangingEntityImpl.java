package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;
import org.by1337.blib.util.Direction;

public abstract class VirtualHangingEntityImpl extends VirtualEntityImpl implements VirtualHangingEntity {
    protected Direction direction = Direction.SOUTH;

    public VirtualHangingEntityImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    public Direction direction() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
        setYaw(to2dValue() * 90F);
    }

    @Override
    public int getCustomEntityData() {
        return switch (direction){
            case DOWN -> 0;
            case UP -> 1;
            case NORTH -> 2;
            case SOUTH -> 3;
            case WEST -> 4;
            case EAST -> 5;
        };
    }

    protected int to2dValue() {
        return switch (direction) {
            case DOWN -> -1;
            case UP -> -1;
            case NORTH -> 2;
            case SOUTH -> 0;
            case WEST -> 1;
            case EAST -> 3;
        };
    }
}
