package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum Pose implements MappedEnum {
    STANDING,
    FALL_FLYING,
    SLEEPING,
    SWIMMING,
    SPIN_ATTACK,
    CROUCHING,
    DYING,
    @SinceMinecraftVersion("1.17.1")
    LONG_JUMPING
    ;
    public static final Codec<Pose> CODEC = DefaultCodecs.createEnumCodec(Pose.class);
    private static final EnumMap<Pose, Integer> TO_ID = new EnumMap<>(Pose.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
