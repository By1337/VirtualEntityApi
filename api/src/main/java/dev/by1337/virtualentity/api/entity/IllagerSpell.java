package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum IllagerSpell implements MappedEnum{
    NONE,
    SUMMON_VEX,
    FANGS,
    WOLOLO,
    DISAPPEAR,
    BLINDNESS;
    public static final Codec<IllagerSpell> CODEC = DefaultCodecs.createEnumCodec(IllagerSpell.class);
    private static final EnumMap<IllagerSpell, Integer> TO_ID = new EnumMap<>(IllagerSpell.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
