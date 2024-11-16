package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

import javax.annotation.Nullable;
import java.util.UUID;

public interface VirtualTamableAnimal extends VirtualAgableMob {
    boolean isTame();

    void setTame(boolean flag);

    boolean isInSittingPose();

    void setInSittingPose(boolean flag);

    @Nullable
    UUID getOwnerUUID();

    void setOwnerUUID(@Nullable UUID param0);
}
