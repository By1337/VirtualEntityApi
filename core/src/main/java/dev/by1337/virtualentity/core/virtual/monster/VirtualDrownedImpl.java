package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualDrowned;

public class VirtualDrownedImpl extends VirtualZombieImpl implements VirtualDrowned {

    public VirtualDrownedImpl() {
        super(VirtualEntityType.DROWNED);
    }
}
