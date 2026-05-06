package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("26.1")
public enum ChickenSoundVariant implements MappedEnum {
    PICKY,
    CLASSIC
    ;
    public static final Codec<ChickenSoundVariant> CODEC = DefaultCodecs.createEnumCodec(ChickenSoundVariant.class);
    private static final EnumMap<ChickenSoundVariant, Integer> TO_ID = new EnumMap<>(ChickenSoundVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
