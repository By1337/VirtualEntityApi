package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualExperienceOrb;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;

public class VirtualExperienceOrbImpl extends VirtualEntityImpl implements VirtualExperienceOrb {
    private static final EntityDataAccessor<Integer> DATA_VALUE;

    public VirtualExperienceOrbImpl() {
        super(VirtualEntityType.EXPERIENCE_ORB);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_VALUE, 1);
    }

    @Override
    public int value() {
        return entityData.get(DATA_VALUE);
    }

    @Override
    public void setValue(int value) {
        entityData.set(DATA_VALUE, value);
    }

    static {
        DATA_VALUE = Mappings.findAccessor("ExperienceOrb", "DATA_VALUE");
    }
}
