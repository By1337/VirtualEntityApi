package dev.by1337.virtualentity.api.virtual.animal.nautilus;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;

@SinceMinecraftVersion("1.21.11")
public interface VirtualAbstractNautilus extends dev.by1337.virtualentity.api.virtual.animal.VirtualTamableAnimal {
    boolean isDashing();

    void setDashing(boolean dashing);
}
