package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualHangingEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;

public abstract class VirtualHangingEntityImpl extends VirtualEntityImpl implements VirtualHangingEntity {
    protected Direction direction = Direction.SOUTH;
    @SinceMinecraftVersion("1.21.6")
    private static final EntityDataAccessor<Direction> DATA_DIRECTION;
    private static final int[] DIRECTION_TO_STEP;

    public VirtualHangingEntityImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (DATA_DIRECTION != null && false) {//в 1.21.6 добавили DATA_DIRECTION но если его использовать direction перестаёт работать
            entityData.define(DATA_DIRECTION, Direction.SOUTH);
        }
    }

    @Override
    public Direction direction() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
        if (DATA_DIRECTION != null && false) {
            entityData.set(DATA_DIRECTION, direction);
            if (direction.isHorizontal()) {
                setPitch(0.f);
                setYaw((float) (to2dValue() * 90));
            } else {
                setPitch((float) (-90 * DIRECTION_TO_STEP[direction.ordinal()]));
                setYaw(0.f);
            }
        } else {
            setYaw(to2dValue() * 90f);
        }
    }

    @Override
    public int getCustomEntityData() {
        return switch (direction) {
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

    static {
        if (Version.is1_21_6orNewer()) {
            DATA_DIRECTION = Mappings.findAccessor("HangingEntity", "DATA_DIRECTION");
        } else {
            DATA_DIRECTION = null;
        }
        Direction[] values = Direction.values();
        DIRECTION_TO_STEP = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            var dir = values[i].getDirection();
            DIRECTION_TO_STEP[i] = dir.x + dir.y + dir.z;
        }
    }
}
