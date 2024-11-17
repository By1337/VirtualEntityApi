package dev.by1337.virtualentity.core.virtual.animal;

import com.google.common.collect.Lists;
import dev.by1337.virtualentity.api.entity.FoxType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VirtualFoxImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualFox {
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID;
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    private static final EntityDataAccessor<Optional<UUID>> DATA_TRUSTED_ID_0;
    private static final EntityDataAccessor<Optional<UUID>> DATA_TRUSTED_ID_1;

    public VirtualFoxImpl() {
        super(VirtualEntityType.FOX);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TRUSTED_ID_0, Optional.empty());
        this.entityData.define(DATA_TRUSTED_ID_1, Optional.empty());
        this.entityData.define(DATA_TYPE_ID, 0);
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
    }

    public List<UUID> getTrustedUUIDs() {
        List<UUID> var1 = Lists.newArrayList();
        var1.add(this.entityData.get(VirtualFoxImpl.DATA_TRUSTED_ID_0).orElse(null));
        var1.add(this.entityData.get(VirtualFoxImpl.DATA_TRUSTED_ID_1).orElse(null));
        return var1;
    }

    public void addTrustedUUID(@Nullable UUID uuid) {
        if (this.entityData.get(VirtualFoxImpl.DATA_TRUSTED_ID_0).isPresent()) {
            this.entityData.set(VirtualFoxImpl.DATA_TRUSTED_ID_1, Optional.ofNullable(uuid));
        } else {
            this.entityData.set(VirtualFoxImpl.DATA_TRUSTED_ID_0, Optional.ofNullable(uuid));
        }
    }

    private void setFlag(int mask, boolean flag) {
        if (flag) {
            this.entityData.set(VirtualFoxImpl.DATA_FLAGS_ID, (byte) (this.entityData.get(VirtualFoxImpl.DATA_FLAGS_ID) | mask));
        } else {
            this.entityData.set(VirtualFoxImpl.DATA_FLAGS_ID, (byte) (this.entityData.get(VirtualFoxImpl.DATA_FLAGS_ID) & ~mask));
        }
    }
    private boolean getFlag(int mask) {
        return (this.entityData.get(VirtualFoxImpl.DATA_FLAGS_ID) & mask) != 0;
    }
    public void setFaceplanted(boolean flag) {
        this.setFlag(64, flag);
    }

    public boolean isDefending() {
        return this.getFlag(128);
    }

    public void setDefending(boolean flag) {
        this.setFlag(128, flag);
    }

    public void setSleeping(boolean flag) {
        this.setFlag(32, flag);
    }

    @Override
    public FoxType getFoxType() {
        return FoxType.values()[this.entityData.get(DATA_TYPE_ID)];
    }

    @Override
    public void setFoxType(FoxType type) {
        this.entityData.set(DATA_TYPE_ID, type.getId());
    }

    @Override
    public boolean isSitting() {
        return this.getFlag(1);
    }

    @Override
    public void setSitting(boolean flag) {
        this.setFlag(1, flag);
    }

    @Override
    public boolean isFaceplanted() {
        return this.getFlag(64);
    }

    @Override
    public boolean isSleeping() {
        return this.getFlag(32);
    }

    @Override
    public boolean isPouncing() {
        return this.getFlag(16);
    }

    @Override
    public void setIsPouncing(boolean flag) {
        this.setFlag(16, flag);
    }

    @Override
    public void setIsCrouching(boolean flag) {
        this.setFlag(4, flag);
    }

    @Override
    public boolean isCrouching() {
        return this.getFlag(4);
    }

    @Override
    public void setIsInterested(boolean flag) {
        this.setFlag(8, flag);
    }

    @Override
    public boolean isInterested() {
        return this.getFlag(8);
    }


    static {
        DATA_TYPE_ID = Mappings.findAccessor("Fox", "DATA_TYPE_ID");
        DATA_FLAGS_ID = Mappings.findAccessor("Fox", "DATA_FLAGS_ID");
        DATA_TRUSTED_ID_0 = Mappings.findAccessor("Fox", "DATA_TRUSTED_ID_0");
        DATA_TRUSTED_ID_1 = Mappings.findAccessor("Fox", "DATA_TRUSTED_ID_1");
    }
}
