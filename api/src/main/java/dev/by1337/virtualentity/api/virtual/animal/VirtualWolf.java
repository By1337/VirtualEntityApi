package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.DyeColor;

public interface VirtualWolf extends VirtualTamableAnimal {
    int getRemainingPersistentAngerTime();

    void setRemainingPersistentAngerTime(int time);

    DyeColor getCollarColor();

    void setCollarColor(DyeColor color);

    void setIsInterested(boolean flag);

    boolean isInterested();
}
