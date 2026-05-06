package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.21.11")
public class VirtualParchedImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualParched {
    public VirtualParchedImpl() {
        super(VirtualEntityType.PARCHED);
    }
}