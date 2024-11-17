package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum InteractionHand implements MappedEnum {
    MAIN_HAND,
    OFF_HAND;
    public static final Codec<InteractionHand> CODEC = DefaultCodecs.createEnumCodec(InteractionHand.class);
    private static final EnumMap<InteractionHand, Integer> TO_ID = new EnumMap<>(InteractionHand.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
