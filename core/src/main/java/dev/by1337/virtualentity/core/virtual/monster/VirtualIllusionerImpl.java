package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualIllusioner;

public class VirtualIllusionerImpl extends VirtualSpellcasterIllagerImpl implements VirtualIllusioner {

    public VirtualIllusionerImpl() {
        super(VirtualEntityType.ILLUSIONER);
    }
}
