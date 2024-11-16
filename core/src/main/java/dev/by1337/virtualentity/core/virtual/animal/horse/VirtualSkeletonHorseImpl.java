package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualSkeletonHorseImpl extends VirtualAbstractHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualSkeletonHorse {

    public VirtualSkeletonHorseImpl() {
        super(VirtualEntityType.SKELETON_HORSE);
    }
}
