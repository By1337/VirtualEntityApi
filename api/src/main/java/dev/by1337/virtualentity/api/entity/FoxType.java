package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum FoxType {
    RED,
    SNOW;
    public static final Codec<FoxType> CODEC = DefaultCodecs.createEnumCodec(FoxType.class);
}
