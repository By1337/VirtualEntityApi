package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.monster.cubemob.VirtualAbstractCubeMobImpl;

public class VirtualSlimeImpl extends VirtualAbstractCubeMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualSlime {
    protected VirtualSlimeImpl(VirtualEntityType type) {
        super(type);
    }

    public VirtualSlimeImpl() {
        super(VirtualEntityType.SLIME);
    }
}
