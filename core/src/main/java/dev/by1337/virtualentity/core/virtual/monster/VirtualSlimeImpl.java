package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

public class VirtualSlimeImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualSlime {
    private static final EntityDataAccessor<Integer> ID_SIZE;

    protected VirtualSlimeImpl(VirtualEntityType type) {
        super(type);
    }

    public VirtualSlimeImpl() {
        super(VirtualEntityType.SLIME);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_SIZE, 1);
    }

    @Override
    public int getSize() {
        return entityData.get(ID_SIZE);
    }

    @Override
    public void setSize(int size) {
        entityData.set(ID_SIZE, size);
    }

    static {
        ID_SIZE = Mappings.findAccessor("Slime", "ID_SIZE");
    }
}
