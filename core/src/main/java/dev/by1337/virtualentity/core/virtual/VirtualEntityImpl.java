package dev.by1337.virtualentity.core.virtual;


import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.Pose;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import net.kyori.adventure.text.Component;
import org.by1337.blib.util.Version;

import javax.annotation.Nullable;
import java.util.Optional;

public abstract class VirtualEntityImpl extends VirtualEntityControllerImpl implements VirtualEntity {
    protected static final EntityDataAccessor<Byte> DATA_SHARED_FLAGS_ID;
    private static final EntityDataAccessor<Integer> DATA_AIR_SUPPLY_ID;
    private static final EntityDataAccessor<Optional<Component>> DATA_CUSTOM_NAME;
    private static final EntityDataAccessor<Boolean> DATA_CUSTOM_NAME_VISIBLE;
    private static final EntityDataAccessor<Boolean> DATA_SILENT;
    private static final EntityDataAccessor<Boolean> DATA_NO_GRAVITY;
    protected static final EntityDataAccessor<Pose> DATA_POSE;
    @SinceMinecraftVersion("1.17.1")
    private static final EntityDataAccessor<Integer> DATA_TICKS_FROZEN;


    public VirtualEntityImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_SHARED_FLAGS_ID, (byte) 0);
        this.entityData.define(DATA_AIR_SUPPLY_ID, 0x12C);
        this.entityData.define(DATA_CUSTOM_NAME_VISIBLE, false);
        this.entityData.define(DATA_CUSTOM_NAME, Optional.empty());
        this.entityData.define(DATA_SILENT, false);
        this.entityData.define(DATA_NO_GRAVITY, false);
        this.entityData.define(DATA_POSE, Pose.STANDING);
        if (Version.VERSION.newerThanOrEqual(Version.V1_17_1))
            this.entityData.define(DATA_TICKS_FROZEN, 0);
    }

    @Override
    public boolean isSilent() {
        return this.entityData.get(DATA_SILENT);
    }

    @Override
    public void setSilent(boolean flag) {
        this.entityData.set(DATA_SILENT, flag);
    }

    @Override
    public boolean isNoGravity() {
        return this.entityData.get(DATA_NO_GRAVITY);
    }

    @Override
    public void setNoGravity(boolean flag) {
        this.entityData.set(DATA_NO_GRAVITY, flag);
    }

    protected boolean getSharedFlag(int param0) {
        return (this.entityData.get(DATA_SHARED_FLAGS_ID) & 1 << param0) != 0;
    }

    protected void setSharedFlag(int param0, boolean param1) {
        byte var3 = this.entityData.get(DATA_SHARED_FLAGS_ID);
        if (param1) {
            this.entityData.set(DATA_SHARED_FLAGS_ID, (byte) (var3 | 1 << param0));
        } else {
            this.entityData.set(DATA_SHARED_FLAGS_ID, (byte) (var3 & ~(1 << param0)));
        }
    }

    @Override
    public int getAirSupply() {
        return this.entityData.get(DATA_AIR_SUPPLY_ID);
    }

    @Override
    public void setAirSupply(int airSupply) {
        this.entityData.set(DATA_AIR_SUPPLY_ID, airSupply);
    }

    @Override
    public void setCustomName(@Nullable Component customName) {
        this.entityData.set(DATA_CUSTOM_NAME, Optional.ofNullable(customName));
    }

    @Override
    @Nullable
    public Component getCustomName() {
        return this.entityData.get(DATA_CUSTOM_NAME).orElse(null);
    }

    @Override
    public boolean hasCustomName() {
        return (this.entityData.get(DATA_CUSTOM_NAME)).isPresent();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        this.entityData.set(DATA_CUSTOM_NAME_VISIBLE, flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.entityData.get(DATA_CUSTOM_NAME_VISIBLE);
    }

    @Override
    public void setGlowing(boolean b) {
        this.setSharedFlag(0x6, b);
    }

    @Override
    public boolean isGlowing() {
        return getSharedFlag(0x6);
    }

    @Override
    public void setShiftKeyDown(boolean flag) {
        this.setSharedFlag(0x1, flag);
    }

    @Override
    public boolean isShiftKeyDown() {
        return this.getSharedFlag(0x1);
    }

    @Override
    public boolean isCrouching() {
        return this.getPose() == Pose.CROUCHING;
    }

    @Override
    public boolean isSprinting() {
        return this.getSharedFlag(0x3);
    }

    @Override
    public void setSprinting(boolean flag) {
        this.setSharedFlag(0x3, flag);
    }

    @Override
    public boolean isSwimming() {
        return this.getSharedFlag(0x4);
    }

    @Override
    public void setSwimming(boolean param0) {
        this.setSharedFlag(0x4, param0);
    }

    @Override
    public void setInvisible(boolean flag) {
        this.setSharedFlag(0x5, flag);
    }

    @Override
    public boolean isInvisible() {
        return this.getSharedFlag(0x5);
    }

    @Override
    public Pose getPose() {
        return this.entityData.get(DATA_POSE);
    }

    @Override
    public void setPose(Pose pose) {
        entityData.set(DATA_POSE, pose);
    }

    @SinceMinecraftVersion("1.17.1")
    public int getTicksFrozen() {
        return this.entityData.get(DATA_TICKS_FROZEN);
    }

    @SinceMinecraftVersion("1.17.1")
    public void setTicksFrozen(int ticks) {
        this.entityData.set(DATA_TICKS_FROZEN, ticks);
    }

    static {
        DATA_SHARED_FLAGS_ID = Mappings.findAccessor("Entity", "DATA_SHARED_FLAGS_ID");
        DATA_AIR_SUPPLY_ID = Mappings.findAccessor("Entity", "DATA_AIR_SUPPLY_ID");
        DATA_CUSTOM_NAME = Mappings.findAccessor("Entity", "DATA_CUSTOM_NAME");
        DATA_CUSTOM_NAME_VISIBLE = Mappings.findAccessor("Entity", "DATA_CUSTOM_NAME_VISIBLE");
        DATA_SILENT = Mappings.findAccessor("Entity", "DATA_SILENT");
        DATA_NO_GRAVITY = Mappings.findAccessor("Entity", "DATA_NO_GRAVITY");
        DATA_POSE = Mappings.findAccessor("Entity", "DATA_POSE");
        if (Version.VERSION.newerThanOrEqual(Version.V1_17_1)) {
            DATA_TICKS_FROZEN = Mappings.findAccessor("Entity", "DATA_TICKS_FROZEN");
        } else {
            DATA_TICKS_FROZEN = null;
        }
    }
}
