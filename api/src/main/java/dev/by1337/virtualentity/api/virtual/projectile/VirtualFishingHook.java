package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;

public interface VirtualFishingHook extends VirtualEntity {
    int getHookedEntity();

    void setHookedEntity(int entityId);

    boolean isBiting();

    void setBiting(boolean biting);
}
