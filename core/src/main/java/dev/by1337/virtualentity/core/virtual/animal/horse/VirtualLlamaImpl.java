package dev.by1337.virtualentity.core.virtual.animal.horse;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;

import javax.annotation.Nullable;

public class VirtualLlamaImpl extends VirtualAbstractChestedHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualLlama {
    private static final boolean OLDER_THAN_1_20_6 = Version.VERSION.olderThan(Version.V1_20_6);
    private static final EntityDataAccessor<Integer> DATA_STRENGTH_ID;
    @RemovedInMinecraftVersion("1.20.6")
    private static final EntityDataAccessor<Integer> DATA_SWAG_ID;
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID;
    private static final BiMap<DyeColor, Material> COLOR_TO_MATERIAL;

    public VirtualLlamaImpl() {
        super(VirtualEntityType.LLAMA);
    }

    protected VirtualLlamaImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_STRENGTH_ID, 0);
        if (OLDER_THAN_1_20_6) this.entityData.define(DATA_SWAG_ID, -1);
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
    @RemovedInMinecraftVersion("1.20.6")
    public void setSwag(@Nullable DyeColor color) {
        if (OLDER_THAN_1_20_6) {
            this.entityData.set(DATA_SWAG_ID, color == null ? -1 : color.getId());
        } else {
            setEquipment(EquipmentSlot.BODY, new ItemStack(COLOR_TO_MATERIAL.getOrDefault(color, Material.WHITE_CARPET)));
        }
    }

    @Nullable
    @Override
    @RemovedInMinecraftVersion("1.20.6")
    public DyeColor getSwag() {
        if (OLDER_THAN_1_20_6) {
            int var1 = this.entityData.get(DATA_SWAG_ID);
            return var1 == -1 ? null : DyeColor.values()[var1];
        } else {
            var item = getEquipment(EquipmentSlot.BODY);
            if (item == null) return null;
            return COLOR_TO_MATERIAL.inverse().get(item.getType());
        }
    }

    @Override
    public void setVariant(int type) {
        this.entityData.set(DATA_VARIANT_ID, clamp(type, 0, 3));
    }

    static {
        DATA_STRENGTH_ID = Mappings.findAccessor("Llama", "DATA_STRENGTH_ID");
        if (OLDER_THAN_1_20_6) {
            DATA_SWAG_ID = Mappings.findAccessor("Llama", "DATA_SWAG_ID");
        } else {
            DATA_SWAG_ID = null;
        }
        DATA_VARIANT_ID = Mappings.findAccessor("Llama", "DATA_VARIANT_ID");

        COLOR_TO_MATERIAL = HashBiMap.create();
        for (DyeColor color : DyeColor.values()) {
            try {
                Material material = Material.valueOf(color.name() + "_CARPET");
                COLOR_TO_MATERIAL.put(color, material);
            } catch (IllegalArgumentException e) {
                COLOR_TO_MATERIAL.put(color, Material.WHITE_CARPET);
            }
        }
    }
}
