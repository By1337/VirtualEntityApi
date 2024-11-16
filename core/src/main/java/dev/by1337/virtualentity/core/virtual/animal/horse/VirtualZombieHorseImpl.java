package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualZombieHorseImpl extends VirtualAbstractHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualZombieHorse {

    public VirtualZombieHorseImpl() {
        super(VirtualEntityType.ZOMBIE_HORSE);
    }
}
