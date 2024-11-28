package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.20.6")
public enum ArmadilloState implements MappedEnum{
    IDLE,
    ROLLING,
    SCARED,
    UNROLLING
    ;
    public static final Codec<ArmadilloState> CODEC = DefaultCodecs.createEnumCodec(ArmadilloState.class);
    private static final EnumMap<ArmadilloState, Integer> TO_ID = new EnumMap<>(ArmadilloState.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
