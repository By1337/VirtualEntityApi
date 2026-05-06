package dev.by1337.virtualentity.api.virtual.animal.nautilus;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ZombieNautilusVariant;

@SinceMinecraftVersion("1.21.11")
public interface VirtualZombieNautilus extends VirtualAbstractNautilus {
    ZombieNautilusVariant getVariant();

    void setVariant(ZombieNautilusVariant variant);
}
