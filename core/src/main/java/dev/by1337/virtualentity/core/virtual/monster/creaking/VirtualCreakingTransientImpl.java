package dev.by1337.virtualentity.core.virtual.monster.creaking;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.21.3")
public class VirtualCreakingTransientImpl extends VirtualCreakingImpl implements dev.by1337.virtualentity.api.virtual.monster.creaking.VirtualCreakingTransient {

    public VirtualCreakingTransientImpl() {
        super(VirtualEntityType.CREAKING_TRANSIENT);
    }
}