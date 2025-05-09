package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.util.Version;

public class VirtualStriderImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.monster.VirtualStrider {
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME;
    private static final EntityDataAccessor<Boolean> DATA_SUFFOCATING;
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    private static final EntityDataAccessor<Boolean> DATA_SADDLE_ID;

    public VirtualStriderImpl() {
        super(VirtualEntityType.STRIDER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BOOST_TIME, 0);
        this.entityData.define(DATA_SUFFOCATING, false);
        if (DATA_SADDLE_ID != null)
            this.entityData.define(DATA_SADDLE_ID, false);
    }

    @Override
    public int getBoostTime() {
        return this.entityData.get(DATA_BOOST_TIME);
    }

    @Override
    public void setBoostTime(int boostTime) {
        this.entityData.set(DATA_BOOST_TIME, boostTime);
    }

    @Override
    public boolean isSuffocating() {
        return this.entityData.get(DATA_SUFFOCATING);
    }

    @Override
    public void setSuffocating(boolean suffocating) {
        this.entityData.set(DATA_SUFFOCATING, suffocating);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public boolean hasSaddle() {
        if (DATA_SADDLE_ID == null) return false;
        return this.entityData.get(DATA_SADDLE_ID);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setSaddle(boolean saddle) {
        if (DATA_SADDLE_ID == null) return;
        this.entityData.set(DATA_SADDLE_ID, saddle);
    }

    static {
        DATA_BOOST_TIME = Mappings.findAccessor("Strider", "DATA_BOOST_TIME");
        DATA_SUFFOCATING = Mappings.findAccessor("Strider", "DATA_SUFFOCATING");
        if (Version.is1_21_5orNewer()) {
            DATA_SADDLE_ID = null;
        } else {
            DATA_SADDLE_ID = Mappings.findAccessor("Strider", "DATA_SADDLE_ID");
        }
    }
}
