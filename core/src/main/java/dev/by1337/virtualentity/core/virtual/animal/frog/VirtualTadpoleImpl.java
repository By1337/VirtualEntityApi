package dev.by1337.virtualentity.core.virtual.animal.frog;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.animal.VirtualAbstractFishImpl;

@SinceMinecraftVersion("1.19.4")
public class VirtualTadpoleImpl extends VirtualAbstractFishImpl {

    public VirtualTadpoleImpl() {
        super(VirtualEntityType.TADPOLE);
    }
}