package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;
import org.by1337.blib.geom.Vec3i;

public class VirtualTurtleImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualTurtle {
    private static final EntityDataAccessor<Boolean> HAS_EGG;
    private static final EntityDataAccessor<Boolean> LAYING_EGG;

    public VirtualTurtleImpl() {
        super(VirtualEntityType.TURTLE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(HAS_EGG, false);
        entityData.define(LAYING_EGG, false);
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setHomePos(Vec3i param0) {
        return;
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public Vec3i getHomePos() {
        return Vec3i.ZERO;
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public void setTravelPos(Vec3i param0) {
        return;
    }

    @Override
    @Deprecated
    @RemovedInMinecraftVersion("1.21.5")
    public Vec3i getTravelPos() {
        return Vec3i.ZERO;
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
        return false;
    }

    @Override
    public void setGoingHome(boolean flag) {
        return;
    }

    @Override
    public boolean isTravelling() {
        return false;
    }

    @Override
    public void setTravelling(boolean flag) {
        return;
    }

    static {

        HAS_EGG = Mappings.findAccessor("Turtle", "HAS_EGG");
        LAYING_EGG = Mappings.findAccessor("Turtle", "LAYING_EGG");
    }
}
