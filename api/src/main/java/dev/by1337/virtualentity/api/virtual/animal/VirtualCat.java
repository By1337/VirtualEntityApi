package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.DyeColor;

public interface VirtualCat extends VirtualTamableAnimal {
    int getCatType();

    void setCatType(int type);

    void setLying(boolean flag);

    boolean isLying();

    void setRelaxStateOne(boolean flag);

    boolean isRelaxStateOne();

    DyeColor getCollarColor();

    void setCollarColor(DyeColor dyeColor);
}
