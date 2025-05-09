package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

public class VirtualTurtleImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualTurtle {
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    @Nullable
    private static final EntityDataAccessor<Vec3i> HOME_POS;
    private static final EntityDataAccessor<Boolean> HAS_EGG;
    private static final EntityDataAccessor<Boolean> LAYING_EGG;
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    @Nullable
    private static final EntityDataAccessor<Vec3i> TRAVEL_POS;
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    @Nullable
    private static final EntityDataAccessor<Boolean> GOING_HOME;
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    @Nullable
    private static final EntityDataAccessor<Boolean> TRAVELLING;

    public VirtualTurtleImpl() {
        super(VirtualEntityType.TURTLE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        if (HOME_POS != null){
            this.entityData.define(HOME_POS, Vec3i.ZERO);
            this.entityData.define(TRAVEL_POS, Vec3i.ZERO);
            this.entityData.define(GOING_HOME, false);
            this.entityData.define(TRAVELLING, false);
        }
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(LAYING_EGG, false);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setHomePos(Vec3i param0) {
        if (HOME_POS == null) return;
        this.entityData.set(HOME_POS, param0);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public Vec3i getHomePos() {
        if (HOME_POS == null) return Vec3i.ZERO;
        return this.entityData.get(HOME_POS);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setTravelPos(Vec3i param0) {
        if (TRAVEL_POS == null) return;
        this.entityData.set(TRAVEL_POS, param0);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public Vec3i getTravelPos() {
        if (TRAVEL_POS == null) return Vec3i.ZERO;
        return this.entityData.get(TRAVEL_POS);
    }

    @Override
    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    @Override
    public void setHasEgg(boolean flag) {
        this.entityData.set(HAS_EGG, flag);
    }

    @Override
    public boolean isLayingEgg() {
        return this.entityData.get(LAYING_EGG);
    }

    @Override
    public void setLayingEgg(boolean flag) {
        this.entityData.set(LAYING_EGG, flag);
    }

    @Override
    public boolean isGoingHome() {
        if (GOING_HOME == null) return false;
        return this.entityData.get(GOING_HOME);
    }

    @Override
    public void setGoingHome(boolean flag) {
        if (GOING_HOME == null) return;
        this.entityData.set(GOING_HOME, flag);
    }

    @Override
    public boolean isTravelling() {
        if (TRAVELLING == null) return false;
        return this.entityData.get(TRAVELLING);
    }

    @Override
    public void setTravelling(boolean flag) {
        if (TRAVELLING == null) return;
        this.entityData.set(TRAVELLING, flag);
    }

    static {
        if (Version.is1_21_4orOlder()) {
            HOME_POS = Mappings.findAccessor("Turtle", "HOME_POS");
            TRAVEL_POS = Mappings.findAccessor("Turtle", "TRAVEL_POS");
            GOING_HOME = Mappings.findAccessor("Turtle", "GOING_HOME");
            TRAVELLING = Mappings.findAccessor("Turtle", "TRAVELLING");
        } else {
            HOME_POS = null;
            TRAVEL_POS = null;
            GOING_HOME = null;
            TRAVELLING = null;
        }
        HAS_EGG = Mappings.findAccessor("Turtle", "HAS_EGG");
        LAYING_EGG = Mappings.findAccessor("Turtle", "LAYING_EGG");
    }
}
