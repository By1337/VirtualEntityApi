package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.21.5")
public enum ChickenVariant implements MappedEnum {
    TEMPERATE,
    COLD,
    WARM
    ;
    public static final Codec<ChickenVariant> CODEC = DefaultCodecs.createEnumCodec(ChickenVariant.class);
    private static final EnumMap<ChickenVariant, Integer> TO_ID = new EnumMap<>(ChickenVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
