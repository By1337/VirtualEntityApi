package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;

@SinceMinecraftVersion("1.21.6")
public class VirtualHangingEntityImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualHangingEntityImpl {
    private static final EntityDataAccessor<Direction> DATA_DIRECTION;

    public VirtualHangingEntityImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (Version.is1_21_6orNewer()){
            entityData.define(DATA_DIRECTION, Direction.SOUTH);
        }
    }
    @SinceMinecraftVersion("1.21.6")
    @Override
    public Direction getDirection() {
        return entityData.get(DATA_DIRECTION);
    }
    @SinceMinecraftVersion("1.21.6")
    @Override
    public void setDirection(Direction direction) {
        entityData.set(DATA_DIRECTION, direction);
    }

    static {
        DATA_DIRECTION = Mappings.findAccessor("HangingEntity", "DATA_DIRECTION");
    }
}