package dev.by1337.virtualentity.core.network;

import blib.com.mojang.serialization.Codec;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

public enum PacketType {
    SET_ENTITY_DATA_PACKET,
    ANIMATE_PACKET,
    ROTATE_HEAD_PACKET,
    TELEPORT_ENTITY_PACKET,
    ADD_ENTITY_PACKET,
    ADD_MOB_PACKET,
    ADD_PLAYER_PACKET,
    ADD_EXPERIENCE_ORB_PACKET,
    ADD_PAINTING_PACKET,
    REMOVE_ENTITIES_PACKET,
    SET_EQUIPMENT_PACKET,
    MOVE_ENTITY_PACKET_POS,
    MOVE_ENTITY_PACKET_POS_ROT,
    MOVE_ENTITY_PACKET_ROT;
    public static final Codec<PacketType> CODEC = DefaultCodecs.createEnumCodec(PacketType.class);
}
