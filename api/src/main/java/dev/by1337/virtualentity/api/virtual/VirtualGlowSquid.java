package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

@SinceMinecraftVersion("1.17.1")
public interface VirtualGlowSquid extends VirtualMob {
    void setDarkTicks(int darkTicks);

    int getDarkTicksRemaining();
}
