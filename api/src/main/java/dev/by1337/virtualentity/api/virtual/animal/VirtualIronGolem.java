package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualIronGolem extends VirtualMob {
    boolean isPlayerCreated();

    void setCrackinessNone();

    void setCrackinessLow();

    void setCrackinessMedium();

    void setCrackinessHigh();

    void setPlayerCreated(boolean flag);

    static VirtualIronGolem create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.IRON_GOLEM, VirtualIronGolem.class);
    }
}
