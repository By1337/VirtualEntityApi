package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

@SinceMinecraftVersion("1.17.1")
public interface VirtualGlowSquid extends VirtualAgeableMob {
    void setDarkTicks(int darkTicks);

    int getDarkTicksRemaining();

    @Override
    @SinceMinecraftVersion("1.21.3")
    boolean isBaby();

    @Override
    @SinceMinecraftVersion("1.21.3")
    void setBaby(boolean flag);
}
