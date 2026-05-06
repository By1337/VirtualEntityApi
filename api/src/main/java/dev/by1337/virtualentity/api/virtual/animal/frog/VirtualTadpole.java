package dev.by1337.virtualentity.api.virtual.animal.frog;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.19.4")
public interface VirtualTadpole extends dev.by1337.virtualentity.api.virtual.animal.VirtualAbstractFish {
    @SinceMinecraftVersion("26.1")
    boolean isAgeLocked();

    @SinceMinecraftVersion("26.1")
    void setAgeLocked(boolean value);

    static VirtualTadpole create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TADPOLE, VirtualTadpole.class);
    }
}
