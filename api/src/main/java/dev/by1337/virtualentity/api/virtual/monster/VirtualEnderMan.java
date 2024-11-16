package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.virtual.VirtualMob;
import org.bukkit.block.data.BlockData;

import javax.annotation.Nullable;

public interface VirtualEnderMan extends VirtualMob {
    void setCarriedBlock(@Nullable BlockData param0);

    @Nullable
    BlockData getCarriedBlock();

    boolean isCreepy();

    void setCreepy(boolean flag);

    boolean hasBeenStaredAt();

    void setBeingStaredAt();
}