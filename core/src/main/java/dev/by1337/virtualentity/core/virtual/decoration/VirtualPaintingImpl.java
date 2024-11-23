package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;

public class VirtualPaintingImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting {
    private static final EntityDataAccessor<PaintingMotive> DATA_PAINTING_VARIANT_ID;

    @RemovedInMinecraftVersion("1.19.4")
    @Deprecated
    private PaintingMotive motive = PaintingMotive.ALBAN;
    @RemovedInMinecraftVersion("1.19.4")
    @Deprecated
    private Direction direction = Direction.EAST;

    public VirtualPaintingImpl() {
        super(VirtualEntityType.PAINTING);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            this.entityData.define(DATA_PAINTING_VARIANT_ID, PaintingMotive.KEBAB);
        }
    }

    @Override
    public PaintingMotive motive() {
        if (DATA_PAINTING_VARIANT_ID != null) return entityData.get(DATA_PAINTING_VARIANT_ID);
        return motive;
    }

    @Override
    public void setMotive(PaintingMotive motive) {
        this.motive = motive;
        if (DATA_PAINTING_VARIANT_ID != null) {
            entityData.define(DATA_PAINTING_VARIANT_ID, motive);
        }
    }

    @Override
    @RemovedInMinecraftVersion("1.19.4")
    @Deprecated
    public Direction direction() {
        return direction;
    }

    @Override
    @RemovedInMinecraftVersion("1.19.4")
    @Deprecated
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            DATA_PAINTING_VARIANT_ID = Mappings.findAccessor("Painting", "DATA_PAINTING_VARIANT_ID");
        } else {
            DATA_PAINTING_VARIANT_ID = null;
        }

    }
}
