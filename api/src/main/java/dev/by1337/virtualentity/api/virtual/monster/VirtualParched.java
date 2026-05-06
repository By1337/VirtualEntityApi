package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

@SinceMinecraftVersion("1.21.11")
public interface VirtualParched extends VirtualMob {
    static VirtualParched create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PARCHED, VirtualParched.class);
    }
}
