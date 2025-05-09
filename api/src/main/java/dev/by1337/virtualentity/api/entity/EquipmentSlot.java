package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum EquipmentSlot implements MappedEnum {
    MAINHAND,
    OFFHAND,
    FEET,
    LEGS,
    CHEST,
    HEAD,
    @SinceMinecraftVersion("1.20.6")
    BODY,
    @SinceMinecraftVersion("1.21.5")
    SADDLE
    ;
    public static final Codec<EquipmentSlot> CODEC = DefaultCodecs.createEnumCodec(EquipmentSlot.class);
    private static final EnumMap<EquipmentSlot, Integer> TO_ID = new EnumMap<>(EquipmentSlot.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
