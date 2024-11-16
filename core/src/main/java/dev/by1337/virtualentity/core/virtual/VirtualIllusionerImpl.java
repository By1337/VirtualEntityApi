package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualIllusionerImpl extends VirtualSpellcasterIllagerImpl implements dev.by1337.virtualentity.api.virtual.VirtualIllusioner {

    public VirtualIllusionerImpl() {
        super(VirtualEntityType.ILLUSIONER);
    }
}
