package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.DyeColor;

import javax.annotation.Nullable;

public interface VirtualLlama extends VirtualAbstractChestedHorse {
    void setStrength(int param0);

    int getStrength();

    int getVariant();

    void setSwag(@Nullable DyeColor param0);

    @Nullable
    DyeColor getSwag();

    // 0 - 3
    void setVariant(int param0);
}
