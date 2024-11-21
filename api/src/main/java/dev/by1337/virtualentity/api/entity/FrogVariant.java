package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.19.4")
public enum FrogVariant implements MappedEnum {
    TEMPERATE,
    COLD,
    WARM;

    public static final Codec<FrogVariant> CODEC = DefaultCodecs.createEnumCodec(FrogVariant.class);
    private static final EnumMap<FrogVariant, Integer> TO_ID = new EnumMap<>(FrogVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
