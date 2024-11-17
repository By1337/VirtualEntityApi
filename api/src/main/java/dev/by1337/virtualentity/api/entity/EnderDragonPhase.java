package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum EnderDragonPhase {
    HOLDING_PATTERN,
    STRAFE_PLAYER,
    LANDING_APPROACH,
    LANDING,
    TAKEOFF,
    SITTING_FLAMING,
    SITTING_SCANNING,
    SITTING_ATTACKING,
    CHARGING_PLAYER,
    DYING,
    HOVERING;
    public static final Codec<EnderDragonPhase> CODEC = DefaultCodecs.createEnumCodec(EnderDragonPhase.class);
}
