package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualWitherSkull extends VirtualEntity {
    boolean isDangerous();

    void setDangerous(boolean dangerous);

    static VirtualWitherSkull create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.WITHER_SKULL, VirtualWitherSkull.class);
    }
}
