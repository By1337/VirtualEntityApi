package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum InteractionHand {
    MAIN_HAND,
    OFF_HAND;
    public static final Codec<InteractionHand> CODEC = DefaultCodecs.createEnumCodec(InteractionHand.class);
}
