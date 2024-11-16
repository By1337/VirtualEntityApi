package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualMob;

public interface VirtualIronGolem extends VirtualMob {
    boolean isPlayerCreated();

    void setCrackinessNone();

    void setCrackinessLow();

    void setCrackinessMedium();

    void setCrackinessHigh();

    void setPlayerCreated(boolean flag);
}
