package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum PandaGene implements MappedEnum {
    NORMAL,
    LAZY,
    WORRIED,
    PLAYFUL,
    BROWN,
    WEAK,
    AGGRESSIVE;
    public static final Codec<PandaGene> CODEC = DefaultCodecs.createEnumCodec(PandaGene.class);
    private static final EnumMap<PandaGene, Integer> TO_ID = new EnumMap<>(PandaGene.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
