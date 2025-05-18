package dev.by1337.virtualentity.core.virtual.item;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.by1337.blib.util.Version;

public class VirtualPrimedTntImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.item.VirtualPrimedTnt {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID;
    @SinceMinecraftVersion("1.20.4")
    private static final EntityDataAccessor<BlockData> DATA_BLOCK_STATE_ID;
    private static final BlockData TNT = Material.TNT.createBlockData();


    public VirtualPrimedTntImpl() {
        super(VirtualEntityType.TNT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FUSE_ID, 80);
        if (DATA_BLOCK_STATE_ID != null) {
            this.entityData.define(DATA_BLOCK_STATE_ID, TNT);
        }
    }

    @Override
    public int getFuse() {
        return entityData.get(DATA_FUSE_ID);
    }

    @Override
    public void setFuse(int fuse) {
        entityData.set(DATA_FUSE_ID, fuse);
    }

    @SinceMinecraftVersion("1.20.4")
    public void setBlockState(BlockData block) {
        this.entityData.set(DATA_BLOCK_STATE_ID, block);
    }

    @SinceMinecraftVersion("1.20.4")
    public BlockData getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE_ID);
    }

    static {
        DATA_FUSE_ID = Mappings.findAccessor("PrimedTnt", "DATA_FUSE_ID");
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_4)) {
            DATA_BLOCK_STATE_ID = Mappings.findAccessor("PrimedTnt", "DATA_BLOCK_STATE_ID");
        } else {
            DATA_BLOCK_STATE_ID = null;
        }
    }
}
