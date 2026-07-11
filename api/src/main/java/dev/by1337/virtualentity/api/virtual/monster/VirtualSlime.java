package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.cubemob.VirtualAbstractCubeMob;

public interface VirtualSlime extends VirtualAbstractCubeMob {
    static VirtualSlime create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SLIME, VirtualSlime.class);
    }
}
