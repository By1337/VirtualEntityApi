package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

@SinceMinecraftVersion("1.21.6")
public interface VirtualHappyGhast extends VirtualAgeableMob {
    void setIsLeashHolder(boolean isLeashHolder);

    void setStaysStill(boolean staysStill);

    boolean isLeashHolder();

    boolean isStaysStill();

    static VirtualHappyGhast create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.HAPPY_GHAST, VirtualHappyGhast.class);
    }
}
