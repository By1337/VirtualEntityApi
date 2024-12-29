package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualSquid extends VirtualAgeableMob {
    @Override
    @SinceMinecraftVersion("1.21.3")
    boolean isBaby();

    @Override
    @SinceMinecraftVersion("1.21.3")
    void setBaby(boolean flag);

    static VirtualSquid create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SQUID, VirtualSquid.class);
    }
}
