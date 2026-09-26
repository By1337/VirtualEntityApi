package dev.by1337.virtualentity.core.virtual.decoration;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.decoration.VirtualCushion;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

@SinceMinecraftVersion("26.3")
public class VirtualCushionImpl extends VirtualEntityImpl implements VirtualCushion {
    private static final EntityDataAccessor<DyeColor> DATA_COLOR = Mappings.findAccessor("Cushion", "DATA_COLOR");

    public VirtualCushionImpl() {
        super(VirtualEntityType.CUSHION);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_COLOR, DyeColor.WHITE);
    }

    @Override
    public DyeColor getColor() {
        return entityData.get(DATA_COLOR);
    }

    @Override
    public void setColor(DyeColor color) {
        entityData.set(DATA_COLOR, color);
    }
}
