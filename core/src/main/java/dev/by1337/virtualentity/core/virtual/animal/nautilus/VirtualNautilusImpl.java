package dev.by1337.virtualentity.core.virtual.animal.nautilus;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;

@SinceMinecraftVersion("1.21.11")
public class VirtualNautilusImpl extends VirtualAbstractNautilusImpl implements dev.by1337.virtualentity.api.virtual.animal.nautilus.VirtualNautilus {
    public VirtualNautilusImpl() {
        super(VirtualEntityType.NAUTILUS);
    }
}