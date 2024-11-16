package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualEvoker;

public class VirtualEvokerImpl extends VirtualSpellcasterIllagerImpl implements VirtualEvoker {

    public VirtualEvokerImpl() {
        super(VirtualEntityType.EVOKER);
    }

}