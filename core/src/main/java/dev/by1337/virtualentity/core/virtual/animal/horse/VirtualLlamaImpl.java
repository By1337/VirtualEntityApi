package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

import javax.annotation.Nullable;

public class VirtualLlamaImpl extends VirtualAbstractChestedHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualLlama {
    private static final EntityDataAccessor<Integer> DATA_STRENGTH_ID;
    private static final EntityDataAccessor<Integer> DATA_SWAG_ID;
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID;

    public VirtualLlamaImpl() {
        super(VirtualEntityType.LLAMA);
    }

    protected VirtualLlamaImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STRENGTH_ID, 0);
        this.entityData.define(DATA_SWAG_ID, -1);
        this.entityData.define(DATA_VARIANT_ID, 0);
    }

    @Override
    public void setStrength(int param0) {
        this.entityData.set(DATA_STRENGTH_ID, Math.max(1, Math.min(5, param0)));
    }

    @Override
    public int getStrength() {
        return this.entityData.get(DATA_STRENGTH_ID);
    }

    // 0 - 3
    @Override
    public int getVariant() {
        return clamp(this.entityData.get(DATA_VARIANT_ID), 0, 3);
    }

   private static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        } else {
            return Math.min(value, max);
        }
    }

    @Override
    public void setSwag(@Nullable DyeColor param0) {
        this.entityData.set(DATA_SWAG_ID, param0 == null ? -1 : param0.getId());
    }

    @Nullable
    @Override
    public DyeColor getSwag() {
        int var1 = this.entityData.get(DATA_SWAG_ID);
        return var1 == -1 ? null : DyeColor.values()[var1];
    }

    @Override
    public void setVariant(int param0) {
        this.entityData.set(DATA_VARIANT_ID, clamp(param0, 0, 3));
    }

    static {
        DATA_STRENGTH_ID = Mappings.findAccessor("Llama", "DATA_STRENGTH_ID");
        DATA_SWAG_ID = Mappings.findAccessor("Llama", "DATA_SWAG_ID");
        DATA_VARIANT_ID = Mappings.findAccessor("Llama", "DATA_VARIANT_ID");
    }
}
