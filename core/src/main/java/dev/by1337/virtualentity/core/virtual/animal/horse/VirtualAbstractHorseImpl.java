package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class VirtualAbstractHorseImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualAbstractHorse {
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS;
    private static final EntityDataAccessor<Optional<UUID>> DATA_ID_OWNER_UUID;

    protected VirtualAbstractHorseImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_FLAGS, (byte)0);
        this.entityData.define(DATA_ID_OWNER_UUID, Optional.empty());
    }

    protected boolean getFlag(int mask) {
        return (this.entityData.get(DATA_ID_FLAGS) & mask) != 0;
    }

    protected void setFlag(int mask, boolean value) {
        byte var3 = this.entityData.get(DATA_ID_FLAGS);
        if (value) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(var3 | mask));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(var3 & ~mask));
        }
    }
    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return (this.entityData.get(DATA_ID_OWNER_UUID)).orElse(null);
    }

    @Override
    public void setOwnerUUID(@Nullable UUID param0) {
        this.entityData.set(DATA_ID_OWNER_UUID, Optional.ofNullable(param0));
    }

    @Override
    public boolean isEating() {
        return this.getFlag(16);
    }
    @Override
    public void setEating(boolean flag) {
        this.setFlag(16, flag);
    }

    @Override
    public boolean isStanding() {
        return this.getFlag(32);
    }
    @Override
    public void setStanding(boolean flag) {
        this.setFlag(32, flag);
    }

    @Override
    public boolean isBred() {
        return this.getFlag(8);
    }
    @Override
    public void setBred(boolean flag) {
        this.setFlag(8, flag);
    }

    @Override
    public boolean isSaddled() {
        return this.getFlag(4);
    }
    @Override
    public void setSaddled(boolean flag) {
        this.setFlag(4, flag);
    }

    @Override
    public boolean isOpenMouth(){
        return this.getFlag(64);
    }

    @Override
    public void setOpenMouth(boolean flag) {
        this.setFlag(64, flag);
    }

    @Override
    public void setTamed(boolean flag) {
        this.setFlag(2, flag);
    }
    @Override
    public boolean isTamed() {
        return getFlag(2);
    }

    static {
        DATA_ID_FLAGS = Mappings.findAccessor("AbstractHorse", "DATA_ID_FLAGS");
        DATA_ID_OWNER_UUID = Mappings.findAccessor("AbstractHorse", "DATA_ID_OWNER_UUID");
    }
}
