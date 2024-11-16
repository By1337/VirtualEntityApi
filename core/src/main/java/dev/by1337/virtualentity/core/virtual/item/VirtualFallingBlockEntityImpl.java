package dev.by1337.virtualentity.core.virtual.item;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.nms.NmsUtil;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.by1337.blib.geom.Vec3i;

public class VirtualFallingBlockEntityImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.item.VirtualFallingBlockEntity {
    private static final EntityDataAccessor<Vec3i> DATA_START_POS;
    private static final int DEFAULT_DATA = NmsUtil.getCombinedId(Material.DIRT.createBlockData()) ;

    private int customData = DEFAULT_DATA;

    public VirtualFallingBlockEntityImpl() {
        super(VirtualEntityType.FALLING_BLOCK);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_START_POS, Vec3i.ZERO);
    }

    @Override
    public void setStartPos(Vec3i vec3i) {
        this.entityData.set(DATA_START_POS, vec3i);
    }

    @Override
    public Vec3i getStartPos(){
        return entityData.get(DATA_START_POS);
    }

    @Override
    public void setBlockType(BlockData block) {
        this.customData = NmsUtil.getCombinedId(block);
    }

    @Override
    public int getCustomEntityData() {
        return customData;
    }

    static {
        DATA_START_POS = Mappings.findAccessor("FallingBlockEntity", "DATA_START_POS");
    }
}
