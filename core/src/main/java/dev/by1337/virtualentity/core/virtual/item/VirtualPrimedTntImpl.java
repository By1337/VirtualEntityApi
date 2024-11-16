package dev.by1337.virtualentity.core.virtual.item;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualPrimedTntImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.item.VirtualPrimedTnt {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID;

    public VirtualPrimedTntImpl() {
        super(VirtualEntityType.TNT);
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    @Override
    public int getFuse() {
        return entityData.get(DATA_FUSE_ID);
    }

    @Override
    public void setFuse(int fuse) {
        entityData.set(DATA_FUSE_ID, fuse);
    }

    static {
        DATA_FUSE_ID = Mappings.findAccessor("PrimedTnt", "DATA_FUSE_ID");
    }
}
