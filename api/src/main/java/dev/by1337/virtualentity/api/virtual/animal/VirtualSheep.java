package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualSheep extends VirtualAgableMob {
    void setColor(DyeColor color);

    DyeColor getColor();

    boolean isSheared();

    void setSheared(boolean param0);
}
