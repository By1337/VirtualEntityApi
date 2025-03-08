package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum EntityAnimation implements MappedEnum {
    SWING_MAIN_ARM,
    @RemovedInMinecraftVersion("1.20.6")
    TAKE_DAMAGE,
    LEAVE_BED,
    SWING_OFFHAND,
    CRITICAL_EFFECT,
    MAGIC_CRITICAL_EFFECT;
    public static final Codec<EntityAnimation> CODEC = DefaultCodecs.createEnumCodec(EntityAnimation.class);
    private static final EnumMap<EntityAnimation, Integer> TO_ID = new EnumMap<>(EntityAnimation.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
