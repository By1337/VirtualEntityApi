package dev.by1337.virtualentity.core.network;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.MappedEnum;
import dev.by1337.virtualentity.api.entity.MappedEnumUtils;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

public enum PacketType implements MappedEnum {
    SET_ENTITY_DATA_PACKET,
    ANIMATE_PACKET,
    ROTATE_HEAD_PACKET,
    TELEPORT_ENTITY_PACKET,
    ADD_ENTITY_PACKET,
    @RemovedInMinecraftVersion("1.19.4")
    ADD_MOB_PACKET,
    @RemovedInMinecraftVersion("1.20.4")
    ADD_PLAYER_PACKET,
    ADD_EXPERIENCE_ORB_PACKET,
    @RemovedInMinecraftVersion("1.19.4")
    ADD_PAINTING_PACKET,
    REMOVE_ENTITIES_PACKET,
    SET_EQUIPMENT_PACKET,
    MOVE_ENTITY_PACKET_POS,
    MOVE_ENTITY_PACKET_POS_ROT,
    MOVE_ENTITY_PACKET_ROT,
    SET_ENTITY_MOTION_PACKET,
    @SinceMinecraftVersion("1.19.4")
    PLAYER_INFO_PACKET,
    SET_PLAYER_TEAM_PACKET,
    ENTITY_EVENT_PACKET,
    ;
    public static final Codec<PacketType> CODEC = DefaultCodecs.createEnumCodec(PacketType.class);
    private static final EnumMap<PacketType, Integer> TO_ID = new EnumMap<>(PacketType.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
