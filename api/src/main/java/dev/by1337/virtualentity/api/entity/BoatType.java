package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@RemovedInMinecraftVersion("1.21.3")
public enum BoatType implements MappedEnum {
    OAK,
    SPRUCE,
    BIRCH,
    JUNGLE,
    ACACIA,
    DARK_OAK,
    @SinceMinecraftVersion("1.19.4")
    MANGROVE,
    @SinceMinecraftVersion("1.19.4")
    CHERRY,
    @SinceMinecraftVersion("1.19.4")
    BAMBOO,
    ;
    public static final Codec<BoatType> CODEC = DefaultCodecs.createEnumCodec(BoatType.class);

    private static final EnumMap<BoatType, Integer> TO_ID = new EnumMap<>(BoatType.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
