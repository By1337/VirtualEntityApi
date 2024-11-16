package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualRavager;
import dev.by1337.virtualentity.core.virtual.raid.VirtualRaiderImpl;

public class VirtualRavagerImpl extends VirtualRaiderImpl implements VirtualRavager {

    public VirtualRavagerImpl() {
        super(VirtualEntityType.RAVAGER);
    }

}