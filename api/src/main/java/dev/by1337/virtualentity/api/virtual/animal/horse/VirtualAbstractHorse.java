package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

import javax.annotation.Nullable;
import java.util.UUID;

public interface VirtualAbstractHorse extends VirtualAgeableMob {
    @RemovedInMinecraftVersion("1.19.4")
    @Nullable
    UUID getOwnerUUID();

    @RemovedInMinecraftVersion("1.19.4")
    void setOwnerUUID(@Nullable UUID param0);

    boolean isEating();

    void setEating(boolean flag);

    boolean isStanding();

    void setStanding(boolean flag);

    boolean isBred();

    void setBred(boolean flag);

    boolean isSaddled();

    void setSaddled(boolean flag);

    boolean isOpenMouth();

    void setOpenMouth(boolean flag);

    void setTamed(boolean flag);

    boolean isTamed();
}
