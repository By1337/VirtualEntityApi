package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualVindicator;


public class VirtualVindicatorImpl extends VirtualRaiderImpl implements VirtualVindicator {

    public VirtualVindicatorImpl() {
        super(VirtualEntityType.VINDICATOR);
    }
}