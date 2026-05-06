package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("26.1")
public enum CowSoundVariant implements MappedEnum {
    MOODY,
    CLASSIC
    ;
    public static final Codec<CowSoundVariant> CODEC = DefaultCodecs.createEnumCodec(CowSoundVariant.class);
    private static final EnumMap<CowSoundVariant, Integer> TO_ID = new EnumMap<>(CowSoundVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
