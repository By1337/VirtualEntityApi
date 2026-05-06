package dev.by1337.virtualentity.core.virtual.animal.camel;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.21.11")
public class VirtualCamelHuskImpl extends VirtualCamelImpl implements dev.by1337.virtualentity.api.virtual.animal.camel.VirtualCamelHusk {
    public VirtualCamelHuskImpl() {
        super(VirtualEntityType.CAMEL_HUSK);
    }
}