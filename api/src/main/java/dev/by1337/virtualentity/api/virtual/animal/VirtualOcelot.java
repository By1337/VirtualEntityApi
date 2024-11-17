package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualOcelot extends VirtualAgeableMob {
    boolean isTrusting();

    void setTrusting(boolean flag);
}
