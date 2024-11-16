package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum EquipmentSlot {
    MAINHAND,
    OFFHAND,
    FEET,
    LEGS,
    CHEST,
    HEAD;
    public static final Codec<EquipmentSlot> CODEC = DefaultCodecs.createEnumCodec(EquipmentSlot.class);
}
