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
    LONG_JUMPING,
    @SinceMinecraftVersion("1.19.4")
    SNIFFING,
    @SinceMinecraftVersion("1.19.4")
    EMERGING,
    @SinceMinecraftVersion("1.19.4")
    SITTING,
    @SinceMinecraftVersion("1.19.4")
    USING_TONGUE,
    @SinceMinecraftVersion("1.19.4")
    ROARING,
    @SinceMinecraftVersion("1.19.4")
    DIGGING,
    @SinceMinecraftVersion("1.19.4")
    CROAKING,
    @SinceMinecraftVersion("1.20.4")
    INHALING,
    @SinceMinecraftVersion("1.20.4")
    SHOOTING,
    @SinceMinecraftVersion("1.20.4")
    SLIDING,
    ;
    public static final Codec<Pose> CODEC = DefaultCodecs.createEnumCodec(Pose.class);
    private static final EnumMap<Pose, Integer> TO_ID = new EnumMap<>(Pose.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
