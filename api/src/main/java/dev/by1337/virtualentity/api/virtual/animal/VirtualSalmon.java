package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.SalmonVariant;

public interface VirtualSalmon extends VirtualAbstractFish {
    @SinceMinecraftVersion("1.21.3")
    void setType(final SalmonVariant type);
}
