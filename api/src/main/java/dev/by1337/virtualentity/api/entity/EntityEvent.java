package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;

import java.util.EnumMap;

/**
 * @see <a href="https://wiki.vg/Entity_statuses">Protocol Entity statuses</a>
 */
public enum EntityEvent implements MappedEnum {
    JUMP,
    DEATH,
    START_ATTACKING,
    STOP_ATTACKING,
    TAMING_FAILED,
    TAMING_SUCCEEDED,
    SHAKE_WETNESS,
    USE_ITEM_COMPLETE,
    EAT_GRASS,
    OFFER_FLOWER,
    LOVE_HEARTS,
    VILLAGER_ANGRY,
    VILLAGER_HAPPY,
    WITCH_HAT_MAGIC,
    ZOMBIE_CONVERTING,
    FIREWORKS_EXPLODE,
    IN_LOVE_HEARTS,
    SQUID_ANIM_SYNCH,
    SILVERFISH_MERGE_ANIM,
    GUARDIAN_ATTACK_SOUND,
    REDUCED_DEBUG_INFO,
    FULL_DEBUG_INFO,
    PERMISSION_LEVEL_ALL,
    PERMISSION_LEVEL_MODERATORS,
    PERMISSION_LEVEL_GAMEMASTERS,
    PERMISSION_LEVEL_ADMINS,
    PERMISSION_LEVEL_OWNERS,
    ATTACK_BLOCKED,
    SHIELD_DISABLED,
    FISHING_ROD_REEL_IN,
    ARMORSTAND_WOBBLE,
    STOP_OFFER_FLOWER,
    TALISMAN_ACTIVATE,
    DOLPHIN_LOOKING_FOR_TREASURE,
    RAVAGER_STUNNED,
    TRUSTING_FAILED,
    TRUSTING_SUCCEEDED,
    VILLAGER_SWEAT,
    BAD_OMEN_TRIGGERED,
    FOX_EAT,
    TELEPORT,
    MAINHAND_BREAK,
    OFFHAND_BREAK,
    HEAD_BREAK,
    CHEST_BREAK,
    LEGS_BREAK,
    FEET_BREAK,
    HONEY_SLIDE,
    HONEY_JUMP,
    SWAP_HANDS,
    CANCEL_SHAKE_WETNESS,
    START_RAM,
    END_RAM,
    POOF,
    DROWNED,
    HURT,
    FROZEN,
    POKED,
    BURNED,
    THORNED,
    @SinceMinecraftVersion("1.19.4")
    TENDRILS_SHIVER,
    @SinceMinecraftVersion("1.19.4")
    SONIC_CHARGE,
    @SinceMinecraftVersion("1.19.4")
    SNIFFER_DIGGING_SOUND,
    ;
    public static final Codec<EntityEvent> CODEC = DefaultCodecs.createEnumCodec(EntityEvent.class);
    private static final EnumMap<EntityEvent, Integer> TO_ID = new EnumMap<>(EntityEvent.class);

    @Override
    public int getId() {
        return MappedEnumUtils.getId(this, TO_ID);
    }
}
