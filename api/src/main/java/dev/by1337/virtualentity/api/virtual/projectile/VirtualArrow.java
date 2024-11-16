package dev.by1337.virtualentity.api.virtual.projectile;

import org.bukkit.Color;
import org.jetbrains.annotations.Nullable;

public interface VirtualArrow extends VirtualAbstractArrow {
    @Nullable Color getColor();

    void setColor(@Nullable Color color);
}
