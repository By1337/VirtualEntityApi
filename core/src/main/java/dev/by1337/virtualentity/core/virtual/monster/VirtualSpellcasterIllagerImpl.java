package dev.by1337.virtualentity.core.virtual.monster;

import dev.by1337.virtualentity.api.entity.IllagerSpell;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.monster.VirtualSpellcasterIllager;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.raid.VirtualRaiderImpl;

public abstract class VirtualSpellcasterIllagerImpl extends VirtualRaiderImpl implements VirtualSpellcasterIllager {
    private static final EntityDataAccessor<Byte> DATA_SPELL_CASTING_ID;

    protected VirtualSpellcasterIllagerImpl(VirtualEntityType type) {
        super(type);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_SPELL_CASTING_ID, (byte)0);
    }

    @Override
    public boolean isCastingSpell() {
        return this.entityData.get(DATA_SPELL_CASTING_ID) > 0;
    }

    @Override
    public void setIsCastingSpell(IllagerSpell spell) {
        this.entityData.set(DATA_SPELL_CASTING_ID, (byte)spell.ordinal());
    }

    static {
        DATA_SPELL_CASTING_ID = Mappings.findAccessor("SpellcasterIllager", "DATA_SPELL_CASTING_ID");
    }
}
