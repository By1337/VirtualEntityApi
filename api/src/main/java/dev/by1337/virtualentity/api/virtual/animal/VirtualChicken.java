package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.ChickenVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualChicken extends VirtualAgeableMob {
    @SinceMinecraftVersion("1.21.5")
    void setVariant(ChickenVariant variant);

    @SinceMinecraftVersion("1.21.5")
    ChickenVariant getVariant();

    static VirtualChicken create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CHICKEN, VirtualChicken.class);
    }
}
