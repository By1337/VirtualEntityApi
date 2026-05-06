package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.20.9")
public enum CopperGolemState implements MappedEnum {
    IDLE,
    GETTING_ITEM,
    GETTING_NO_ITEM,
    DROPPING_ITEM,
    DROPPING_NO_ITEM;
    ;
    public static final Codec<CopperGolemState> CODEC = DefaultCodecs.createEnumCodec(CopperGolemState.class);
    private static final EnumMap<CopperGolemState, Integer> TO_ID = new EnumMap<>(CopperGolemState.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
