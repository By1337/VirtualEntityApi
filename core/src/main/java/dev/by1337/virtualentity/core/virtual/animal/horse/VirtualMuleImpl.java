package dev.by1337.virtualentity.core.virtual.animal.horse;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualMuleImpl extends VirtualAbstractChestedHorseImpl implements dev.by1337.virtualentity.api.virtual.animal.horse.VirtualMule {

    public VirtualMuleImpl() {
        super(VirtualEntityType.MULE);
    }

}
