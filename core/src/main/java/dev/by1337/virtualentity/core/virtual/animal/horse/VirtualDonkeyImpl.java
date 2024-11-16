package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualDonkeyImpl extends VirtualAbstractChestedHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualDonkey {

    public VirtualDonkeyImpl() {
        super(VirtualEntityType.DONKEY);
    }

}
