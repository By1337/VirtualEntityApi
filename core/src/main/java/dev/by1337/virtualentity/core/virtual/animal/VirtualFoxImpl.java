package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.FoxType;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

import java.util.Optional;
import java.util.UUID;

public class VirtualFoxImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualFox {
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

    @Override
    public FoxType getFoxType() {
        return FoxType.values()[this.entityData.get(DATA_TYPE_ID)];
    }

    @Override
    public void setFoxType(FoxType type) {
        this.entityData.set(DATA_TYPE_ID, type.ordinal());
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
