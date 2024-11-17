package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.17.1")
public enum AxolotVariant implements MappedEnum {
    LUCY,
    WILD,
    GOLD,
    CYAN,
    BLUE,
    ;
    public static final Codec<AxolotVariant> CODEC = DefaultCodecs.createEnumCodec(AxolotVariant.class);
    private static final EnumMap<AxolotVariant, Integer> TO_ID = new EnumMap<>(AxolotVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
