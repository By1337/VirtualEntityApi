package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public abstract class VirtualTamableAnimalImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualTamableAnimal {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID;

    protected VirtualTamableAnimalImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
    }

    @Override
    public boolean isTame() {
        return (this.entityData.get(DATA_FLAGS_ID) & 4) != 0;
    }

    @Override
    public void setTame(boolean flag) {
        byte var2 = this.entityData.get(DATA_FLAGS_ID);
        if (flag) {
            this.entityData.set(DATA_FLAGS_ID, (byte) (var2 | 4));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte) (var2 & -5));
        }
    }

    @Override
    public boolean isInSittingPose() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    @Override
    public void setInSittingPose(boolean flag) {
        byte var2 = this.entityData.get(DATA_FLAGS_ID);
        if (flag) {
            this.entityData.set(DATA_FLAGS_ID, (byte) (var2 | 1));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte) (var2 & -2));
        }
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }

    @Override
    public void setOwnerUUID(@Nullable UUID param0) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(param0));
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("TamableAnimal", "DATA_FLAGS_ID");
        DATA_OWNERUUID_ID = Mappings.findAccessor("TamableAnimal", "DATA_OWNERUUID_ID");
    }
}