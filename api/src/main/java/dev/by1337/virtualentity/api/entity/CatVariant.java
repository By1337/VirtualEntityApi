package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.19.4")
public enum CatVariant implements MappedEnum {
    RED,
    RAGDOLL,
    JELLIE,
    TABBY,
    WHITE,
    ALL_BLACK,
    BRITISH_SHORTHAIR,
    BLACK,
    SIAMESE,
    CALICO,
    PERSIAN,
    ;
    public static final Codec<CatVariant> CODEC = DefaultCodecs.createEnumCodec(CatVariant.class);
    private static final EnumMap<CatVariant, Integer> TO_ID = new EnumMap<>(CatVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
