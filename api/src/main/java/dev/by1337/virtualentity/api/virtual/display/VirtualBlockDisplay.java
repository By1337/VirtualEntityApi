package dev.by1337.virtualentity.api.virtual.display;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.bukkit.block.data.BlockData;

@SinceMinecraftVersion("1.19.4")
public interface VirtualBlockDisplay extends VirtualDisplay {
    void setBlock(BlockData block);
}
