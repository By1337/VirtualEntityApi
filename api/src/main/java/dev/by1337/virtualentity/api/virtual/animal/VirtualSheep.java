package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualSheep extends VirtualAgeableMob {
    void setColor(DyeColor color);

    DyeColor getColor();

    boolean isSheared();

    void setSheared(boolean param0);
}
