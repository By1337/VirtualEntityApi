package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.CowVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualCow extends VirtualAgeableMob {

    @SinceMinecraftVersion("1.21.5")
    void setVariant(CowVariant variant);

    @SinceMinecraftVersion("1.21.5")
    CowVariant getVariant();

    static VirtualCow create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.COW, VirtualCow.class);
    }
}
