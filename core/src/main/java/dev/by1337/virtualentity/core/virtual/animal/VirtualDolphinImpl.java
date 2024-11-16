package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualMobImpl;
import org.by1337.blib.geom.Vec3i;

public class VirtualDolphinImpl extends VirtualMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualDolphin {
    private static final EntityDataAccessor<Vec3i> TREASURE_POS;
    private static final EntityDataAccessor<Boolean> GOT_FISH;
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL;

    public VirtualDolphinImpl() {
        super(VirtualEntityType.DOLPHIN);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TREASURE_POS, Vec3i.ZERO);
        this.entityData.define(GOT_FISH, false);
        this.entityData.define(MOISTNESS_LEVEL, 2400);
    }

    @Override
    public void setTreasurePos(Vec3i pos) {
        this.entityData.set(TREASURE_POS, pos);
    }

    @Override
    public Vec3i getTreasurePos() {
        return this.entityData.get(TREASURE_POS);
    }

    @Override
    public boolean gotFish() {
        return this.entityData.get(GOT_FISH);
    }

    @Override
    public void setGotFish(boolean flag) {
        this.entityData.set(GOT_FISH, flag);
    }

    @Override
    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    @Override
    public void setMoisntessLevel(int level) {
        this.entityData.set(MOISTNESS_LEVEL, level);
    }


    static {
        TREASURE_POS = Mappings.findAccessor("Dolphin", "TREASURE_POS");
        GOT_FISH = Mappings.findAccessor("Dolphin", "GOT_FISH");
        MOISTNESS_LEVEL = Mappings.findAccessor("Dolphin", "MOISTNESS_LEVEL");
    }
}
