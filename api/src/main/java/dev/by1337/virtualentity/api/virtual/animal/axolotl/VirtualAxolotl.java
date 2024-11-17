package dev.by1337.virtualentity.api.virtual.animal.axolotl;

import dev.by1337.virtualentity.api.entity.AxolotVariant;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualAxolotl extends VirtualAgeableMob {
    void setVariant(AxolotVariant variant);

    void setPlayingDead(boolean flag);

    boolean isPlayingDead();

    void setFromBucket(boolean flag);

    boolean isFromBucket();
}
