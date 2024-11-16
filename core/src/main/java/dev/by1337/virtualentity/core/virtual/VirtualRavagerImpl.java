package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualRavager;

public class VirtualRavagerImpl extends VirtualRaiderImpl implements VirtualRavager {

    public VirtualRavagerImpl() {
        super(VirtualEntityType.RAVAGER);
    }

}