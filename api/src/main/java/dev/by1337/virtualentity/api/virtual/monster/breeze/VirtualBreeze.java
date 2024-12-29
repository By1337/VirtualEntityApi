package dev.by1337.virtualentity.api.virtual.monster.breeze;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

@SinceMinecraftVersion("1.20.4")
public interface VirtualBreeze extends VirtualMob {

    static VirtualBreeze create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BREEZE, VirtualBreeze.class);
    }
}
