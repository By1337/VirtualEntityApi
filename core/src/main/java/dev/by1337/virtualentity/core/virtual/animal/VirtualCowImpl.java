package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualCowImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualCow {

    public VirtualCowImpl() {
        super(VirtualEntityType.COW);
    }
}