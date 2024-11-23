package dev.by1337.virtualentity.core.virtual.display;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

@SinceMinecraftVersion("1.19.4")
public class VirtualBlockDisplayImpl extends VirtualDisplayImpl implements dev.by1337.virtualentity.api.virtual.display.VirtualBlockDisplay {
    private static final EntityDataAccessor<BlockData> DATA_BLOCK_STATE_ID;

    public VirtualBlockDisplayImpl() {
        super(VirtualEntityType.BLOCK_DISPLAY);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BLOCK_STATE_ID, Material.DIRT.createBlockData());
    }

    @Override
    public void setBlock(BlockData block) {
        this.entityData.set(DATA_BLOCK_STATE_ID, block);
    }

    static {
        DATA_BLOCK_STATE_ID = Mappings.findAccessor("BlockDisplay", "DATA_BLOCK_STATE_ID");
    }
}