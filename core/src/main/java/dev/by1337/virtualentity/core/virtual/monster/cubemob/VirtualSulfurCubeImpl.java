package dev.by1337.virtualentity.core.virtual.monster.cubemob;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

@SinceMinecraftVersion("26.2")
public class VirtualSulfurCubeImpl extends VirtualAbstractCubeMobImpl implements dev.by1337.virtualentity.api.virtual.monster.cubemob.VirtualSulfurCube {
    private static final EntityDataAccessor<Integer> MAX_FUSE;
    private static final EntityDataAccessor<Boolean> FROM_BUCKET;

    public VirtualSulfurCubeImpl() {
        super(VirtualEntityType.SULFUR_CUBE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MAX_FUSE, -1);
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public int getMaxFuse() {
        return entityData.get(MAX_FUSE);
    }

    @Override
    public void setMaxFuse(int fuse) {
        entityData.set(MAX_FUSE, fuse);
    }

    @Override
    public boolean isFromBucket() {
        return entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean flag) {
        entityData.set(FROM_BUCKET, false);
    }

    static {
        MAX_FUSE = Mappings.findAccessor("SulfurCube", "MAX_FUSE");
        FROM_BUCKET = Mappings.findAccessor("SulfurCube", "FROM_BUCKET");
    }
}