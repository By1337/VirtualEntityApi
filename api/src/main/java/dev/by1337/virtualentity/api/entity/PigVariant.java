package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.21.5")
public enum PigVariant implements MappedEnum {
    TEMPERATE,
    COLD,
    WARM
    ;
    public static final Codec<PigVariant> CODEC = DefaultCodecs.createEnumCodec(PigVariant.class);
    private static final EnumMap<PigVariant, Integer> TO_ID = new EnumMap<>(PigVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
