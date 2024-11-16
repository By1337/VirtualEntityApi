package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum Pose {
    STANDING,
    FALL_FLYING,
    SLEEPING,
    SWIMMING,
    SPIN_ATTACK,
    CROUCHING,
    DYING;
    public static final Codec<Pose> CODEC = DefaultCodecs.createEnumCodec(Pose.class);
}
