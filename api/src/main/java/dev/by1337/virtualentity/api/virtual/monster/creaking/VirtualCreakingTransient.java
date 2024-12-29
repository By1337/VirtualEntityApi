package dev.by1337.virtualentity.api.virtual.monster.creaking;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.21.3")
@RemovedInMinecraftVersion("1.21.4")
public interface VirtualCreakingTransient extends VirtualCreaking {

    static VirtualCreaking create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CREAKING_TRANSIENT, VirtualCreaking.class);
    }
}
