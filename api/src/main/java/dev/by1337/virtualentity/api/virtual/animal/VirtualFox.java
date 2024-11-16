package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.FoxType;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface VirtualFox extends VirtualAgableMob {
    FoxType getFoxType();

    void setFoxType(FoxType type);

    List<UUID> getTrustedUUIDs();

    void addTrustedUUID(@Nullable UUID uuid);

    boolean isSitting();

    void setSitting(boolean flag);

    boolean isFaceplanted();

    void setFaceplanted(boolean flag);

    boolean isDefending();

    void setDefending(boolean flag);

    boolean isSleeping();

    void setSleeping(boolean flag);

    boolean isPouncing();

    void setIsPouncing(boolean flag);

    void setIsCrouching(boolean flag);

    boolean isCrouching();

    void setIsInterested(boolean flag);

    boolean isInterested();
}
