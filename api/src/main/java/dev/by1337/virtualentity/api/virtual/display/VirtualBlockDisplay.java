package dev.by1337.virtualentity.api.virtual.display;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import org.bukkit.block.data.BlockData;

@SinceMinecraftVersion("1.19.4")
public interface VirtualBlockDisplay extends VirtualDisplay {
    void setBlock(BlockData block);

    static VirtualBlockDisplay create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.BLOCK_DISPLAY, VirtualBlockDisplay.class);
    }
}
