package dev.by1337.virtualentity.core.virtual.monster.cubemob;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

@SinceMinecraftVersion("26.2")
public class VirtualAbstractCubeMobImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.monster.cubemob.VirtualAbstractCubeMob {
    private static final EntityDataAccessor<Integer> ID_SIZE;

    public VirtualAbstractCubeMobImpl(VirtualEntityType type) {
        super(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        if (ID_SIZE != null)
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
        if (ServerVersion.is26_2orNewer())
            ID_SIZE = Mappings.findAccessor("AbstractCubeMob", "ID_SIZE");
        else ID_SIZE = null;
    }
}