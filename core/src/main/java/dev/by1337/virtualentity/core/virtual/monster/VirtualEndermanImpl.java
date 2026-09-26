package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.core.ServerVersion;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;
import org.bukkit.block.data.BlockData;

import javax.annotation.Nullable;
import java.util.Optional;

public class VirtualEndermanImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualEnderMan {
    private static final EntityDataAccessor<Optional<BlockData>> DATA_CARRY_STATE;
    private static final EntityDataAccessor<Boolean> DATA_CREEPY;
    private static final EntityDataAccessor<Boolean> DATA_STARED_AT;

    public VirtualEndermanImpl() {
        super(VirtualEntityType.ENDERMAN);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CARRY_STATE, Optional.empty());
        this.entityData.define(DATA_CREEPY, false);
        this.entityData.define(DATA_STARED_AT, false);
    }

    @Override
    public void setCarriedBlock(@Nullable BlockData param0) {
        this.entityData.set(DATA_CARRY_STATE, Optional.ofNullable(param0));
    }

    @Nullable
    @Override
    public BlockData getCarriedBlock() {
        return this.entityData.get(DATA_CARRY_STATE).orElse(null);
    }

    @Override
    public boolean isCreepy() {
        return this.entityData.get(DATA_CREEPY);
    }

    @Override
    public void setCreepy(boolean flag) {
        this.entityData.set(DATA_CREEPY, flag);
    }

    @Override
    public boolean hasBeenStaredAt() {
        return this.entityData.get(DATA_STARED_AT);
    }

    @Override
    public void setBeingStaredAt() {
        this.entityData.set(DATA_STARED_AT, true);
    }

    static {
        String entity = ServerVersion.CURRENT_PROTOCOL == 777 ? "Enderman" : "EnderMan";
        DATA_CARRY_STATE = Mappings.findAccessor(entity, "DATA_CARRY_STATE");
        DATA_CREEPY = Mappings.findAccessor(entity, "DATA_CREEPY");
        DATA_STARED_AT = Mappings.findAccessor(entity, "DATA_STARED_AT");
    }
}
