package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.Color;
import org.jetbrains.annotations.Nullable;

public interface VirtualArrow extends VirtualAbstractArrow {
    @Nullable Color getColor();

    void setColor(@Nullable Color color);

    static VirtualArrow create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ARROW, VirtualArrow.class);
    }
}
