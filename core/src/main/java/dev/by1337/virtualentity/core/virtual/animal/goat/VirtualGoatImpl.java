package dev.by1337.virtualentity.core.virtual.animal.goat;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualGoatImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.goat.VirtualGoat {
    private static final EntityDataAccessor<Boolean> DATA_IS_SCREAMING_GOAT;

    private static final EntityDataAccessor<Boolean> DATA_HAS_LEFT_HORN;

    private static final EntityDataAccessor<Boolean> DATA_HAS_RIGHT_HORN;

    public VirtualGoatImpl() {
        super(VirtualEntityType.GOAT);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_SCREAMING_GOAT, false);
        entityData.define(DATA_HAS_LEFT_HORN, true);
        entityData.define(DATA_HAS_RIGHT_HORN, true);
    }

    @Override
    public boolean isScreamingGoat() {
        return this.entityData.get(DATA_IS_SCREAMING_GOAT);
    }

    @Override
    public void setScreamingGoat(boolean flag) {
        this.entityData.set(DATA_IS_SCREAMING_GOAT, flag);
    }

    @Override
    public void setHasLeftHorn(boolean flag) {
        entityData.set(DATA_HAS_LEFT_HORN, flag);
    }

    @Override
    public boolean hasLeftHorn() {
        return entityData.get(DATA_HAS_LEFT_HORN);
    }

    @Override
    public void setHasRightHorn(boolean flag) {
        entityData.set(DATA_HAS_RIGHT_HORN, flag);
    }

    @Override
    public boolean hasRightHorn() {
        return entityData.get(DATA_HAS_RIGHT_HORN);
    }

    static {
        DATA_IS_SCREAMING_GOAT = Mappings.findAccessor("Goat", "DATA_IS_SCREAMING_GOAT");
        DATA_HAS_LEFT_HORN = Mappings.findAccessor("Goat", "DATA_HAS_LEFT_HORN");
        DATA_HAS_RIGHT_HORN = Mappings.findAccessor("Goat", "DATA_HAS_RIGHT_HORN");

    }
}
