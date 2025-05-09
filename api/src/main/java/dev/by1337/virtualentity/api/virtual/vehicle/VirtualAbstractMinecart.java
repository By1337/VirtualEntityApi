package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public interface VirtualAbstractMinecart extends VirtualVehicleEntity {

    @Deprecated
    int getDisplayBlock();

    @Deprecated
    default void setDisplayBlock(BlockData displayBlock) {
        setCustomDisplayBlock(displayBlock);
    }

    int getDisplayOffset();

    void setDisplayOffset(int offset);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    boolean hasCustomDisplay();

    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    void setCustomDisplay(boolean customDisplay);

    void setCustomDisplayBlock(@Nullable BlockData data);

    Optional<BlockData> getCustomDisplayBlock();
}
