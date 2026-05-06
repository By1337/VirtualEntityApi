package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.animal.VirtualBee;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.jetbrains.annotations.Nullable;

public class VirtualBeeImpl extends VirtualAgeableMobImpl implements VirtualBee {
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    @Deprecated
    @RemovedInMinecraftVersion("1.21.11")
    @Nullable
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME;
    @SinceMinecraftVersion("1.21.11")
    private static final EntityDataAccessor<Long> DATA_ANGER_END_TIME;

    public VirtualBeeImpl() {
        super(VirtualEntityType.BEE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);
        if (ServerVersion.is1_21_11orNewer()) {
            this.entityData.define(DATA_ANGER_END_TIME, -1L);
        } else {
            this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        }
    }

    @SinceMinecraftVersion("1.21.11")
    public long getAngerEndTime() {
        if (DATA_ANGER_END_TIME == null) return 0;
        return entityData.get(DATA_ANGER_END_TIME);
    }

    @SinceMinecraftVersion("1.21.11")
    public void setAngerEndTime(long time) {
        if (DATA_ANGER_END_TIME == null) return;
        entityData.set(DATA_ANGER_END_TIME, time);
    }

    @Override
    @RemovedInMinecraftVersion("1.21.11")
    public int getRemainingPersistentAngerTime() {
        if (DATA_REMAINING_ANGER_TIME == null) return 0;
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    @RemovedInMinecraftVersion("1.21.11")
    public void setRemainingPersistentAngerTime(int time) {
        if (DATA_REMAINING_ANGER_TIME == null) return;
        this.entityData.set(DATA_REMAINING_ANGER_TIME, time);
    }

    @Override
    public boolean hasStung() {
        return this.getFlag(0x4);
    }

    @Override
    public void setHasStung(boolean flag) {
        this.setFlag(0x4, flag);
    }

    @Override
    public boolean isRolling() {
        return this.getFlag(0x2);
    }

    @Override
    public void setRolling(boolean flag) {
        this.setFlag(0x2, flag);
    }

    private void setFlag(int mask, boolean flag) {
        if (flag) {
            this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) | mask));
        } else {
            this.entityData.set(DATA_FLAGS_ID, (byte) (this.entityData.get(DATA_FLAGS_ID) & ~mask));
        }
    }

    private boolean getFlag(int mask) {
        return (this.entityData.get(DATA_FLAGS_ID) & mask) != 0;
    }

    static {
        DATA_FLAGS_ID = Mappings.findAccessor("Bee", "DATA_FLAGS_ID");
        if (ServerVersion.is1_21_10orNewer()) {
            DATA_ANGER_END_TIME = Mappings.findAccessor("Bee", "DATA_ANGER_END_TIME");
            DATA_REMAINING_ANGER_TIME = null;
        } else {
            DATA_REMAINING_ANGER_TIME = Mappings.findAccessor("Bee", "DATA_REMAINING_ANGER_TIME");
            DATA_ANGER_END_TIME = null;
        }
    }
}
