package dev.by1337.virtualentity.api.virtual.animal.horse;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.DyeColor;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntityController;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public interface VirtualLlama extends VirtualAbstractChestedHorse {
    void setStrength(int param0);

    int getStrength();

    int getVariant();

    /**
     * @deprecated use {@link VirtualEntityController#setEquipment(EquipmentSlot, ItemStack)}
     * in versions higher than 1.20.6 this method will also work, but it is advisable not to use it
     */
    @RemovedInMinecraftVersion("1.20.6")
    void setSwag(@Nullable DyeColor color);

    /**
     * @deprecated use {@link VirtualEntityController#getEquipment(EquipmentSlot)}
     * in versions higher than 1.20.6 this method will also work, but it is advisable not to use it
     */
    @Nullable
    @RemovedInMinecraftVersion("1.20.6")
    DyeColor getSwag();

    // 0 - 3
    void setVariant(int type);

    static VirtualLlama create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.LLAMA, VirtualLlama.class);
    }
}
