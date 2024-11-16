package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualExperienceOrb;

public class VirtualExperienceOrbImpl extends VirtualEntityImpl implements VirtualExperienceOrb {
    private int value = 1;

    public VirtualExperienceOrbImpl() {
        super(VirtualEntityType.EXPERIENCE_ORB);
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}
