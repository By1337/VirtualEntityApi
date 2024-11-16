package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Color;
import org.jetbrains.annotations.Nullable;

public class VirtualArrowImpl extends VirtualAbstractArrowImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualArrow {
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR;

    public VirtualArrowImpl() {
        super(VirtualEntityType.ARROW);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }
    @Override
    public @Nullable Color getColor(){
        var val = entityData.get(ID_EFFECT_COLOR);
        if (val == -1) return null;
        return Color.fromRGB(val);
    }
    @Override
    public void setColor(@Nullable Color color){
        entityData.set(ID_EFFECT_COLOR, color == null ? -1 : color.asRGB());
    }

    static {
        ID_EFFECT_COLOR = Mappings.findAccessor("Arrow", "ID_EFFECT_COLOR");
    }
}
