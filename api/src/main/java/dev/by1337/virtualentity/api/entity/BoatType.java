package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum BoatType implements MappedEnum {
    OAK,
    SPRUCE,
    BIRCH,
    JUNGLE,
    ACACIA,
    DARK_OAK;
    public static final Codec<BoatType> CODEC = DefaultCodecs.createEnumCodec(BoatType.class);

    private static final EnumMap<BoatType, Integer> TO_ID = new EnumMap<>(BoatType.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
