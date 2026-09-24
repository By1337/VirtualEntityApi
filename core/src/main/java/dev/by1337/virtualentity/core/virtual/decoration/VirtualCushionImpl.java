package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualCushionImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.decoration.VirtualCushion {
    private static final EntityDataAccessor<DyeColor> DATA_COLOR;

    public VirtualCushionImpl() {
        super(VirtualEntityType.CUSHION);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_COLOR, DyeColor.WHITE);
    }

    @Override
    public DyeColor color() {
        return this.entityData.get(DATA_COLOR);
    }

    @Override
    public void setColor(DyeColor color) {
        this.entityData.set(DATA_COLOR, color);
    }

    static {
        DATA_COLOR = Mappings.findAccessor("Cushion", "DATA_COLOR");
    }
}
