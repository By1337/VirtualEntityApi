package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.20.6")
public enum WolfVariant implements MappedEnum{
    SNOWY,
    ASHEN,
    STRIPED,
    SPOTTED,
    WOODS,
    BLACK,
    CHESTNUT,
    RUSTY,
    PALE,
    ;
    public static final Codec<WolfVariant> CODEC = DefaultCodecs.createEnumCodec(WolfVariant.class);
    private static final EnumMap<WolfVariant, Integer> TO_ID = new EnumMap<>(WolfVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
