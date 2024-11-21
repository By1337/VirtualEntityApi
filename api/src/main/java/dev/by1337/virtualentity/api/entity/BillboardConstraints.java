package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

@SinceMinecraftVersion("1.19.4")
public enum BillboardConstraints implements MappedEnum {
    FIXED,
    VERTICAL,
    HORIZONTAL,
    CENTER,
    ;
    public static final Codec<BillboardConstraints> CODEC = DefaultCodecs.createEnumCodec(BillboardConstraints.class);
    private static final EnumMap<BillboardConstraints, Integer> TO_ID = new EnumMap<>(BillboardConstraints.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
