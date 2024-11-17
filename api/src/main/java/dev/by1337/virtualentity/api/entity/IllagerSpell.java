package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum IllagerSpell {
    NONE,
    SUMMON_VEX,
    FANGS,
    WOLOLO,
    DISAPPEAR,
    BLINDNESS;
    public static final Codec<IllagerSpell> CODEC = DefaultCodecs.createEnumCodec(IllagerSpell.class);
}
