package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.21.5")
public enum WolfSoundVariant implements MappedEnum {
    BIG,
    SAD,
    ANGRY,
    CUTE,
    PUGLIN,
    CLASSIC,
    GRUMPY,
    ;
    public static final Codec<WolfSoundVariant> CODEC = DefaultCodecs.createEnumCodec(WolfSoundVariant.class);
    private static final EnumMap<WolfSoundVariant, Integer> TO_ID = new EnumMap<>(WolfSoundVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
