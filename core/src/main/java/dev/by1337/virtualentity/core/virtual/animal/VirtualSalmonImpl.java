package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualSalmonImpl extends VirtualAbstractFishImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualSalmon {

    public VirtualSalmonImpl() {
        super(VirtualEntityType.SALMON);
    }
}
