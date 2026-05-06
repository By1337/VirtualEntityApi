package dev.by1337.virtualentity.api.virtual.animal.camel;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.21.11")
public interface VirtualCamelHusk extends VirtualCamel {

    static VirtualCamelHusk create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.CAMEL_HUSK, VirtualCamelHusk.class);
    }
}
