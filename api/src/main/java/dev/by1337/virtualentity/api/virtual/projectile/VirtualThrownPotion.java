package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
@Deprecated
@RemovedInMinecraftVersion("1.21.5")
public interface VirtualThrownPotion extends dev.by1337.virtualentity.api.virtual.projectile.VirtualThrowableItemProjectile {

    static VirtualThrownPotion create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.POTION, VirtualThrownPotion.class);
    }
}
