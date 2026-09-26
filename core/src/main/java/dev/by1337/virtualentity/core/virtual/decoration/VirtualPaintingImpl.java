package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.entity.PaintingMotive;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualHangingEntityImpl;

public class VirtualPaintingImpl extends VirtualHangingEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting {
    private static final EntityDataAccessor<PaintingMotive> DATA_PAINTING_VARIANT_ID;

    public VirtualPaintingImpl() {
        super(VirtualEntityType.PAINTING);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PAINTING_VARIANT_ID, PaintingMotive.KEBAB);
    }

    @Override
    public PaintingMotive motive() {
        return entityData.get(DATA_PAINTING_VARIANT_ID);
    }

    @Override
    public void setMotive(PaintingMotive motive) {
        entityData.set(DATA_PAINTING_VARIANT_ID, motive);
    }

    static {
        DATA_PAINTING_VARIANT_ID = Mappings.findAccessor("Painting", "DATA_PAINTING_VARIANT_ID");

    }
}
