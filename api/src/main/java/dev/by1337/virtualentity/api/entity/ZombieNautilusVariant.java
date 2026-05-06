package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.21.11")
public enum ZombieNautilusVariant implements MappedEnum {
    TEMPERATE,
    WARM
    ;
    public static final Codec<ZombieNautilusVariant> CODEC = DefaultCodecs.createEnumCodec(ZombieNautilusVariant.class);
    private static final EnumMap<ZombieNautilusVariant, Integer> TO_ID = new EnumMap<>(ZombieNautilusVariant.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
