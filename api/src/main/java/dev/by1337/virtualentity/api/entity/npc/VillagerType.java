package dev.by1337.virtualentity.api.entity.npc;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.entity.MappedEnum;
import dev.by1337.virtualentity.api.entity.MappedEnumUtils;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum VillagerType implements MappedEnum {
    DESERT,
    JUNGLE,
    PLAINS,
    SAVANNA,
    SNOW,
    SWAMP,
    TAIGA;
    public static final Codec<VillagerType> CODEC = DefaultCodecs.createEnumCodec(VillagerType.class);
    private static final EnumMap<VillagerType, Integer> TO_ID = new EnumMap<>(VillagerType.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
