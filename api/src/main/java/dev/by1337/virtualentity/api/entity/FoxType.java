package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum FoxType implements MappedEnum {
    RED,
    SNOW;
    public static final Codec<FoxType> CODEC = DefaultCodecs.createEnumCodec(FoxType.class);
    private static final EnumMap<FoxType, Integer> TO_ID = new EnumMap<>(FoxType.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
