package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualWitherSkull extends VirtualEntity {
    boolean isDangerous();

    void setDangerous(boolean dangerous);
}
