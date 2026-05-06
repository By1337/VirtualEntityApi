package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("26.1")
public enum PigSoundVariant implements MappedEnum {
    BIG,
    MINI,
    CLASSIC
    ;
    public static final Codec<PigSoundVariant> CODEC = DefaultCodecs.createEnumCodec(PigSoundVariant.class);
    private static final EnumMap<PigSoundVariant, Integer> TO_ID = new EnumMap<>(PigSoundVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
