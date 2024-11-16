package dev.by1337.virtualentity.api.virtual.animal;

import com.google.common.collect.Lists;
import dev.by1337.virtualentity.api.entity.FoxType;
import dev.by1337.virtualentity.api.virtual.VirtualAgableMob;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VirtualFox extends VirtualAgableMob {
    FoxType getFoxType();

    void setFoxType(FoxType type);

    default List<UUID> getTrustedUUIDs() {
        List<UUID> var1 = Lists.newArrayList();
        var1.add(this.entityData.get(VirtualFoxImpl.DATA_TRUSTED_ID_0).orElse(null));
        var1.add(this.entityData.get(VirtualFoxImpl.DATA_TRUSTED_ID_1).orElse(null));
        return var1;
    }

    default void addTrustedUUID(@Nullable UUID uuid) {
        if (this.entityData.get(VirtualFoxImpl.DATA_TRUSTED_ID_0).isPresent()) {
            this.entityData.set(VirtualFoxImpl.DATA_TRUSTED_ID_1, Optional.ofNullable(uuid));
        } else {
            this.entityData.set(VirtualFoxImpl.DATA_TRUSTED_ID_0, Optional.ofNullable(uuid));
        }
    }

    default void setFlag(int mask, boolean flag) {
        if (flag) {
            this.entityData.set(VirtualFoxImpl.DATA_FLAGS_ID, (byte) (this.entityData.get(VirtualFoxImpl.DATA_FLAGS_ID) | mask));
        } else {
            this.entityData.set(VirtualFoxImpl.DATA_FLAGS_ID, (byte) (this.entityData.get(VirtualFoxImpl.DATA_FLAGS_ID) & ~mask));
        }

    }

    default boolean getFlag(int mask) {
        return (this.entityData.get(VirtualFoxImpl.DATA_FLAGS_ID) & mask) != 0;
    }

    boolean isSitting();

    void setSitting(boolean flag);

    boolean isFaceplanted();

    default void setFaceplanted(boolean flag) {
        this.setFlag(64, flag);
    }

    default boolean isDefending() {
        return this.getFlag(128);
    }

    default void setDefending(boolean flag) {
        this.setFlag(128, flag);
    }

    boolean isSleeping();

    default void setSleeping(boolean flag) {
        this.setFlag(32, flag);
    }

    boolean isPouncing();

    void setIsPouncing(boolean flag);

    void setIsCrouching(boolean flag);

    boolean isCrouching();

    void setIsInterested(boolean flag);

    boolean isInterested();
}
