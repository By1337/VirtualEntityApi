package dev.by1337.virtualentity.api.virtual.vehicle;

import org.bukkit.block.data.BlockData;

public interface VirtualAbstractMinecart extends VirtualVehicleEntity {

    int getDisplayBlock();

    void setDisplayBlock(BlockData displayBlock);

    int getDisplayOffset();

    void setDisplayOffset(int offset);

    boolean hasCustomDisplay();

    void setCustomDisplay(boolean customDisplay);
}
