package dev.by1337.virtualentity.core.virtual.item;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public class VirtualPrimedTntImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.item.VirtualPrimedTnt {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID;

    private static final EntityDataAccessor<BlockData> DATA_BLOCK_STATE_ID;
    private static final BlockData TNT = Material.TNT.createBlockData();

    public VirtualPrimedTntImpl() {
        super(VirtualEntityType.TNT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FUSE_ID, 80);
        this.entityData.define(DATA_BLOCK_STATE_ID, TNT);
    }

    @Override
    public int getFuse() {
        return entityData.get(DATA_FUSE_ID);
    }

    @Override
    public void setFuse(int fuse) {
        entityData.set(DATA_FUSE_ID, fuse);
    }

    public void setBlockState(BlockData block) {
        this.entityData.set(DATA_BLOCK_STATE_ID, block);
    }

    public BlockData getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE_ID);
    }

    static {
        DATA_FUSE_ID = Mappings.findAccessor("PrimedTnt", "DATA_FUSE_ID");
        DATA_BLOCK_STATE_ID = Mappings.findAccessor("PrimedTnt", "DATA_BLOCK_STATE_ID");
    }
}
