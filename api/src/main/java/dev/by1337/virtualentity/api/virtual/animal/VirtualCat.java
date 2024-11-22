package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.CatVariant;
import dev.by1337.virtualentity.api.entity.DyeColor;

public interface VirtualCat extends VirtualTamableAnimal {
    @RemovedInMinecraftVersion("1.19.4")
    int getCatType();

    @RemovedInMinecraftVersion("1.19.4")
    void setCatType(int type);

    void setLying(boolean flag);

    boolean isLying();

    void setRelaxStateOne(boolean flag);

    boolean isRelaxStateOne();

    DyeColor getCollarColor();

    void setCollarColor(DyeColor dyeColor);

    @SinceMinecraftVersion("1.19.4")
    CatVariant getVariant();

    @SinceMinecraftVersion("1.19.4")
    void setVariant(CatVariant variant);
}
