package dev.by1337.virtualentity.api.entity.npc;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum VillagerProfession {
    NONE,
    ARMORER,
    BUTCHER,
    CARTOGRAPHER,
    CLERIC,
    FARMER,
    FISHERMAN,
    FLETCHER,
    LEATHERWORKER,
    LIBRARIAN,
    MASON,
    NITWIT,
    SHEPHERD,
    TOOLSMITH,
    WEAPONSMITH;
    public static final Codec<VillagerProfession> CODEC = DefaultCodecs.createEnumCodec(VillagerProfession.class);
}
