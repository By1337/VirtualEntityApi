package dev.by1337.virtualentity.core.virtual.projectile;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;

public class VirtualAbstractArrowImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.projectile.VirtualAbstractArrow {
    private static final EntityDataAccessor<Byte> ID_FLAGS;
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL;

    public VirtualAbstractArrowImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_FLAGS, (byte) 0);
        this.entityData.define(PIERCE_LEVEL, (byte) 0);
    }

    private void setFlag(int mask, boolean flag) {
        byte var3 =  this.entityData.get(ID_FLAGS);
        if (flag) {
            this.entityData.set(ID_FLAGS, (byte) (var3 | mask));
        } else {
            this.entityData.set(ID_FLAGS, (byte) (var3 & ~mask));
        }
    }

    @Override
    public void setCritArrow(boolean flag) {
        this.setFlag(1, flag);
    }
    @Override
    public boolean isCritArrow() {
        return (this.entityData.get(ID_FLAGS) & 1) != 0;
    }

    @Override
    public void setPierceLevel(byte value) {
        this.entityData.set(PIERCE_LEVEL, value);
    }

    @Override
    public byte getPierceLevel() {
        return this.entityData.get(PIERCE_LEVEL);
    }

    @Override
    public void setNoPhysics(boolean param0) {
        this.setFlag(2, param0);
    }

    @Override
    public boolean isNoPhysics() {
        return (this.entityData.get(ID_FLAGS) & 2) != 0;
    }

    @Override
    public void setShotFromCrossbow(boolean flag) {
        this.setFlag(4, flag);
    }
    @Override
    public boolean isShotFromCrossbow() {
        return (this.entityData.get(ID_FLAGS) & 4) != 0;
    }

    static {
        ID_FLAGS = Mappings.findAccessor("AbstractArrow", "ID_FLAGS");
        PIERCE_LEVEL = Mappings.findAccessor("AbstractArrow", "PIERCE_LEVEL");
    }
}
