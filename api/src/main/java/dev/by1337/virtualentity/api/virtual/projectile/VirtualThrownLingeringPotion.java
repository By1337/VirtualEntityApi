package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.21.5")
public interface VirtualThrownLingeringPotion extends VirtualThrowableItemProjectile{
    static VirtualThrownLingeringPotion create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.LINGERING_POTION, VirtualThrownLingeringPotion.class);
    }
}
