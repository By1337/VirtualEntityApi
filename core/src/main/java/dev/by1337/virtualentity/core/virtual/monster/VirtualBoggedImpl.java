package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.20.6")
public class VirtualBoggedImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualBogged {
    private static final EntityDataAccessor<Boolean> DATA_SHEARED;

    public VirtualBoggedImpl() {
        super(VirtualEntityType.BOGGED);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_SHEARED, false);
    }

    @Override
    public boolean isSheared() {
        return entityData.get(DATA_SHEARED);
    }

    @Override
    public void setSheared(boolean sheared) {
        entityData.set(DATA_SHEARED, sheared);
    }

    static {
        DATA_SHEARED = Mappings.findAccessor("Bogged", "DATA_SHEARED");
    }
}