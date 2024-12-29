package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSkeleton extends VirtualMob {
    @SinceMinecraftVersion("1.17.1")
    boolean isStrayConversion();

    @SinceMinecraftVersion("1.17.1")
    void setStrayConversion(boolean flag);

    static VirtualSkeleton create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SKELETON, VirtualSkeleton.class);
    }
}
