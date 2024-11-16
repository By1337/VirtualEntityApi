package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;

public class VirtualEvokerImpl extends VirtualSpellcasterIllagerImpl implements dev.by1337.virtualentity.api.virtual.VirtualEvoker {

    public VirtualEvokerImpl() {
        super(VirtualEntityType.EVOKER);
    }

}