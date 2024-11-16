package dev.by1337.virtualentity.api.entity.npc;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.entity.EntityAnimation;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum VillagerType {
    DESERT,
    JUNGLE,
    PLAINS,
    SAVANNA,
    SNOW,
    SWAMP,
    TAIGA;
    public static final Codec<VillagerType> CODEC = DefaultCodecs.createEnumCodec(VillagerType.class);
}
