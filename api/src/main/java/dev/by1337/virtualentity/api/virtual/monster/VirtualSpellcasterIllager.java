package dev.by1337.virtualentity.api.virtual.monster;

import dev.by1337.virtualentity.api.entity.IllagerSpell;
import dev.by1337.virtualentity.api.virtual.raid.VirtualRaider;

public interface VirtualSpellcasterIllager extends VirtualRaider {
    boolean isCastingSpell();

    void setIsCastingSpell(IllagerSpell spell);
}
