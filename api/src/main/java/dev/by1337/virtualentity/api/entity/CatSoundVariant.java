package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("26.1")
public enum CatSoundVariant implements MappedEnum {
    CLASSIC,
    ROYAL
    ;
    public static final Codec<CatSoundVariant> CODEC = DefaultCodecs.createEnumCodec(CatSoundVariant.class);
    private static final EnumMap<CatSoundVariant, Integer> TO_ID = new EnumMap<>(CatSoundVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
