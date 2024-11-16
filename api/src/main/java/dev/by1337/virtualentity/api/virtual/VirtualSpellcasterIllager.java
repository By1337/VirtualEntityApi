package dev.by1337.virtualentity.api.virtual;

import dev.by1337.virtualentity.api.entity.IllagerSpell;

public interface VirtualSpellcasterIllager extends VirtualRaider {
    boolean isCastingSpell();

    void setIsCastingSpell(IllagerSpell spell);
}
