package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.WolfVariant;

public interface VirtualWolf extends VirtualTamableAnimal {
    int getRemainingPersistentAngerTime();

    void setRemainingPersistentAngerTime(int time);

    DyeColor getCollarColor();

    void setCollarColor(DyeColor color);

    void setIsInterested(boolean flag);

    boolean isInterested();
    @SinceMinecraftVersion("1.20.6")
    void setWolfVariant(WolfVariant variant);
    @SinceMinecraftVersion("1.20.6")
    WolfVariant getWolfVariant();
}
