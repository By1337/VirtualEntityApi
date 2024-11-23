package dev.by1337.virtualentity.api.virtual.animal.sniffer;

import dev.by1337.virtualentity.api.entity.SnifferState;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualSniffer extends VirtualAgeableMob {
    void setState(SnifferState state);

    SnifferState getState();

    void setDropSeedAtTick(int tick);

    int getDropSeedAtTick();
}
