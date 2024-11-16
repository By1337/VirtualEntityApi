package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualHuskImpl extends VirtualZombieImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualHusk {

    public VirtualHuskImpl() {
        super(VirtualEntityType.HUSK);
    }
}
