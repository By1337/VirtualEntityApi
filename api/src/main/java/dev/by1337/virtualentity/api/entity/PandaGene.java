package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum PandaGene {
    NORMAL,
    LAZY,
    WORRIED,
    PLAYFUL,
    BROWN,
    WEAK,
    AGGRESSIVE;
    public static final Codec<PandaGene> CODEC = DefaultCodecs.createEnumCodec(PandaGene.class);
}
