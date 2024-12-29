package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.SalmonVariant;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public interface VirtualSalmon extends VirtualAbstractFish {
    @SinceMinecraftVersion("1.21.3")
    void setType(final SalmonVariant type);

    static VirtualSalmon create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SALMON, VirtualSalmon.class);
    }
}
