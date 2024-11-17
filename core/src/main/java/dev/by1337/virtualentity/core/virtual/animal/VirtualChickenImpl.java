package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.VirtualAgeableMobImpl;

public class VirtualChickenImpl extends VirtualAgeableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualChicken {

    public VirtualChickenImpl() {
        super(VirtualEntityType.CHICKEN);
    }
}