package dev.by1337.virtualentity.api.virtual.projectile;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;

/// If you do not specify hooked entity, FishingHook will not appear on client because "Failed to recreate fishing hook on client. null (id: 0) is not a valid owner".
public interface VirtualFishingHook extends VirtualEntity {
    int getHookedEntity();

    void setHookedEntity(int entityId);

    boolean isBiting();

    void setBiting(boolean biting);

    static VirtualFishingHook create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.FISHING_BOBBER, VirtualFishingHook.class);
    }
}
