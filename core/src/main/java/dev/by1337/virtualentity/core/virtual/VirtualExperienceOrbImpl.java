package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualExperienceOrb;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import org.by1337.blib.util.Version;

public class VirtualExperienceOrbImpl extends VirtualEntityImpl implements VirtualExperienceOrb {
    private int value = 1;
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
        if (DATA_VALUE != null){
            return entityData.get(DATA_VALUE);
        }
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
        if (DATA_VALUE != null){
            entityData.set(DATA_VALUE, value);
        }
    }

    static {
        if (Version.is1_21_5orNewer()){
            DATA_VALUE = Mappings.findAccessor("ExperienceOrb", "DATA_VALUE");
        }else {
            DATA_VALUE = null;
        }
    }
}
