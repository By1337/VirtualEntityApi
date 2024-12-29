package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualSlime extends VirtualMob {
    int getSize();

    void setSize(int size);

    static VirtualSlime create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.SLIME, VirtualSlime.class);
    }
}
