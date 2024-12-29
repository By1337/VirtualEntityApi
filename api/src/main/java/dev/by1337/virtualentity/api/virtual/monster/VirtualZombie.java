package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualZombie extends VirtualMob {
    boolean isDrownConverting();

    boolean isBaby();

    void setBaby(boolean flag);

    void stopDrowning();

    void startDrownedConversion();

    static VirtualZombie create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.ZOMBIE, VirtualZombie.class);
    }
}
