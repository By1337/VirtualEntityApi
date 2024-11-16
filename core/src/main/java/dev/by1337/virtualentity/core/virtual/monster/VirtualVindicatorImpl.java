package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualVindicator;


public class VirtualVindicatorImpl extends VirtualRaiderImpl implements VirtualVindicator {

    public VirtualVindicatorImpl() {
        super(VirtualEntityType.VINDICATOR);
    }
}