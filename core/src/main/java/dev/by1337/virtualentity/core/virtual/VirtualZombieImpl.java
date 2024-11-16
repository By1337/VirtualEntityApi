package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualZombie;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualZombieImpl extends VirtualMobImpl implements VirtualZombie {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;
    private static final EntityDataAccessor<Integer> DATA_SPECIAL_TYPE_ID ;
    public static final EntityDataAccessor<Boolean> DATA_DROWNED_CONVERSION_ID;


    protected VirtualZombieImpl(VirtualEntityType type) {
        super(type);
    }

    public VirtualZombieImpl() {
        super(VirtualEntityType.ZOMBIE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_BABY_ID, false);
        entityData.define(DATA_SPECIAL_TYPE_ID, 0);
        entityData.define(DATA_DROWNED_CONVERSION_ID, false);
    }

    @Override
    public boolean isDrownConverting() {
        return entityData.get(DATA_DROWNED_CONVERSION_ID);
    }

    @Override
    public boolean isBaby() {
        return entityData.get(DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean flag) {
        entityData.set(DATA_BABY_ID, flag);
    }

    @Override
    public void stopDrowning() {
        entityData.set(DATA_DROWNED_CONVERSION_ID, false);
    }

    @Override
    public void startDrownedConversion() {
        entityData.set(DATA_DROWNED_CONVERSION_ID, true);
    }

    static {
        DATA_BABY_ID = Mappings.findAccessor("Zombie", "DATA_BABY_ID");
        DATA_SPECIAL_TYPE_ID = Mappings.findAccessor("Zombie", "DATA_SPECIAL_TYPE_ID");
        DATA_DROWNED_CONVERSION_ID = Mappings.findAccessor("Zombie", "DATA_DROWNED_CONVERSION_ID");
    }
}
