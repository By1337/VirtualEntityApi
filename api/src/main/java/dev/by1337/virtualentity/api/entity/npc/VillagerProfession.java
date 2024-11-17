package dev.by1337.virtualentity.api.entity.npc;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.entity.MappedEnum;
import dev.by1337.virtualentity.api.entity.MappedEnumUtils;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum VillagerProfession implements MappedEnum {
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
    private static final EnumMap<VillagerProfession, Integer> TO_ID = new EnumMap<>(VillagerProfession.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
