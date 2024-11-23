package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;

@SinceMinecraftVersion("1.19.4")
public class VirtualWardenImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualWarden {
    private static final EntityDataAccessor<Integer> CLIENT_ANGER_LEVEL;

    public VirtualWardenImpl() {
        super(VirtualEntityType.WARDEN);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLIENT_ANGER_LEVEL, 0);
    }

    @Override
    public int getClientAngerLevel() {
        return this.entityData.get(CLIENT_ANGER_LEVEL);
    }

    @Override
    public void setClientAngerLevel(int anger) {
        this.entityData.set(CLIENT_ANGER_LEVEL, anger);
    }

    static {
        CLIENT_ANGER_LEVEL = Mappings.findAccessor("Warden", "CLIENT_ANGER_LEVEL");
    }
}