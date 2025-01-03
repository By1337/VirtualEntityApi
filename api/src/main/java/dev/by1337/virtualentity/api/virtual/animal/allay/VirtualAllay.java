package dev.by1337.virtualentity.api.virtual.animal.allay;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

@SinceMinecraftVersion("1.19.4")
public interface VirtualAllay extends VirtualMob {
    void setDancing(boolean dancing);

    boolean isDancing();

    void setCanDuplicate(boolean canDuplicate);

    boolean isCanDuplicate();

    static VirtualAllay create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ALLAY, VirtualAllay.class);
    }
}
