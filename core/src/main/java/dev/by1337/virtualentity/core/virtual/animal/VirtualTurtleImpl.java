package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;
import org.by1337.blib.geom.Vec3i;

public class VirtualTurtleImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualTurtle {
    private static final EntityDataAccessor<Vec3i> HOME_POS;
    private static final EntityDataAccessor<Boolean> HAS_EGG;
    private static final EntityDataAccessor<Boolean> LAYING_EGG;
    private static final EntityDataAccessor<Vec3i> TRAVEL_POS;
    private static final EntityDataAccessor<Boolean> GOING_HOME;
    private static final EntityDataAccessor<Boolean> TRAVELLING;

    public VirtualTurtleImpl() {
        super(VirtualEntityType.TURTLE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME_POS, Vec3i.ZERO);
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(TRAVEL_POS, Vec3i.ZERO);
        this.entityData.define(GOING_HOME, false);
        this.entityData.define(TRAVELLING, false);
        this.entityData.define(LAYING_EGG, false);
    }

    @Override
    public void setHomePos(Vec3i param0) {
        this.entityData.set(HOME_POS, param0);
    }

    @Override
    public Vec3i getHomePos() {
        return this.entityData.get(HOME_POS);
    }

    @Override
    public void setTravelPos(Vec3i param0) {
        this.entityData.set(TRAVEL_POS, param0);
    }

    @Override
    public Vec3i getTravelPos() {
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
        return this.entityData.get(GOING_HOME);
    }

    @Override
    public void setGoingHome(boolean flag) {
        this.entityData.set(GOING_HOME, flag);
    }

    @Override
    public boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    @Override
    public void setTravelling(boolean flag) {
        this.entityData.set(TRAVELLING, flag);
    }

    static {
        HOME_POS = Mappings.findAccessor("Turtle", "HOME_POS");
        HAS_EGG = Mappings.findAccessor("Turtle", "HAS_EGG");
        LAYING_EGG = Mappings.findAccessor("Turtle", "LAYING_EGG");
        TRAVEL_POS = Mappings.findAccessor("Turtle", "TRAVEL_POS");
        GOING_HOME = Mappings.findAccessor("Turtle", "GOING_HOME");
        TRAVELLING = Mappings.findAccessor("Turtle", "TRAVELLING");
    }
}
