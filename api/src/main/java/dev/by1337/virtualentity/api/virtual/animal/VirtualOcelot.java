package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

public interface VirtualOcelot extends VirtualAgableMob {
    boolean isTrusting();

    void setTrusting(boolean flag);
}
