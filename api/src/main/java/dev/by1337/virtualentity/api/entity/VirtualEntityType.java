package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;
import org.by1337.blib.util.Version;

public enum VirtualEntityType {
    AREA_EFFECT_CLOUD(Version.V1_16_5),
    ARMOR_STAND(Version.V1_16_5),
    ARROW(Version.V1_16_5),
    BAT(Version.V1_16_5),
    BEE(Version.V1_16_5),
    BLAZE(Version.V1_16_5),
    BOAT(Version.V1_16_5),
    CAT(Version.V1_16_5),
    CAVE_SPIDER(Version.V1_16_5),
    CHICKEN(Version.V1_16_5),
    COD(Version.V1_16_5),
    COW(Version.V1_16_5),
    CREEPER(Version.V1_16_5),
    DOLPHIN(Version.V1_16_5),
    DONKEY(Version.V1_16_5),
    DRAGON_FIREBALL(Version.V1_16_5),
    DROWNED(Version.V1_16_5),
    ELDER_GUARDIAN(Version.V1_16_5),
    END_CRYSTAL(Version.V1_16_5),
    ENDER_DRAGON(Version.V1_16_5),
    ENDERMAN(Version.V1_16_5),
    ENDERMITE(Version.V1_16_5),
    EVOKER(Version.V1_16_5),
    EVOKER_FANGS(Version.V1_16_5),
    EXPERIENCE_ORB(Version.V1_16_5),
    EYE_OF_ENDER(Version.V1_16_5),
    FALLING_BLOCK(Version.V1_16_5),
    FIREWORK_ROCKET(Version.V1_16_5),
    FOX(Version.V1_16_5),
    GHAST(Version.V1_16_5),
    GIANT(Version.V1_16_5),
    GUARDIAN(Version.V1_16_5),
    HOGLIN(Version.V1_16_5),
    HORSE(Version.V1_16_5),
    HUSK(Version.V1_16_5),
    ILLUSIONER(Version.V1_16_5),
    IRON_GOLEM(Version.V1_16_5),
    ITEM(Version.V1_16_5),
    ITEM_FRAME(Version.V1_16_5),
    FIREBALL(Version.V1_16_5),
    LEASH_KNOT(Version.V1_16_5),
    LIGHTNING_BOLT(Version.V1_16_5),
    LLAMA(Version.V1_16_5),
    LLAMA_SPIT(Version.V1_16_5),
    MAGMA_CUBE(Version.V1_16_5),
    MINECART(Version.V1_16_5),
    CHEST_MINECART(Version.V1_16_5),
    COMMAND_BLOCK_MINECART(Version.V1_16_5),
    FURNACE_MINECART(Version.V1_16_5),
    HOPPER_MINECART(Version.V1_16_5),
    SPAWNER_MINECART(Version.V1_16_5),
    TNT_MINECART(Version.V1_16_5),
    MULE(Version.V1_16_5),
    MOOSHROOM(Version.V1_16_5),
    OCELOT(Version.V1_16_5),
    PAINTING(Version.V1_16_5),
    PANDA(Version.V1_16_5),
    PARROT(Version.V1_16_5),
    PHANTOM(Version.V1_16_5),
    PIG(Version.V1_16_5),
    PIGLIN(Version.V1_16_5),
    PIGLIN_BRUTE(Version.V1_16_5),
    PILLAGER(Version.V1_16_5),
    POLAR_BEAR(Version.V1_16_5),
    TNT(Version.V1_16_5),
    PUFFERFISH(Version.V1_16_5),
    RABBIT(Version.V1_16_5),
    RAVAGER(Version.V1_16_5),
    SALMON(Version.V1_16_5),
    SHEEP(Version.V1_16_5),
    SHULKER(Version.V1_16_5),
    SHULKER_BULLET(Version.V1_16_5),
    SILVERFISH(Version.V1_16_5),
    SKELETON(Version.V1_16_5),
    SKELETON_HORSE(Version.V1_16_5),
    SLIME(Version.V1_16_5),
    SMALL_FIREBALL(Version.V1_16_5),
    SNOW_GOLEM(Version.V1_16_5),
    SNOWBALL(Version.V1_16_5),
    SPECTRAL_ARROW(Version.V1_16_5),
    SPIDER(Version.V1_16_5),
    SQUID(Version.V1_16_5),
    STRAY(Version.V1_16_5),
    STRIDER(Version.V1_16_5),
    EGG(Version.V1_16_5),
    ENDER_PEARL(Version.V1_16_5),
    EXPERIENCE_BOTTLE(Version.V1_16_5),
    POTION(Version.V1_16_5),
    TRIDENT(Version.V1_16_5),
    TRADER_LLAMA(Version.V1_16_5),
    TROPICAL_FISH(Version.V1_16_5),
    TURTLE(Version.V1_16_5),
    VEX(Version.V1_16_5),
    VILLAGER(Version.V1_16_5),
    VINDICATOR(Version.V1_16_5),
    WANDERING_TRADER(Version.V1_16_5),
    WITCH(Version.V1_16_5),
    WITHER(Version.V1_16_5),
    WITHER_SKELETON(Version.V1_16_5),
    WITHER_SKULL(Version.V1_16_5),
    WOLF(Version.V1_16_5),
    ZOGLIN(Version.V1_16_5),
    ZOMBIE(Version.V1_16_5),
    ZOMBIE_HORSE(Version.V1_16_5),
    ZOMBIE_VILLAGER(Version.V1_16_5),
    ZOMBIFIED_PIGLIN(Version.V1_16_5),
    PLAYER(Version.V1_16_5),
    FISHING_BOBBER(Version.V1_16_5),
    @SinceMinecraftVersion("1.17.1")
    AXOLOTL(Version.V1_17_1),
    @SinceMinecraftVersion("1.17.1")
    GOAT(Version.V1_17_1),
    @SinceMinecraftVersion("1.17.1")
    GLOW_ITEM_FRAME(Version.V1_17_1),
    @SinceMinecraftVersion("1.17.1")
    GLOW_SQUID(Version.V1_17_1),

    @SinceMinecraftVersion("1.19.4")
    WARDEN(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    TEXT_DISPLAY(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    CAMEL(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    SNIFFER(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    ITEM_DISPLAY(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    FROG(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    BLOCK_DISPLAY(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    CHEST_BOAT(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    INTERACTION(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    ALLAY(Version.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    TADPOLE(Version.V1_19_4),
    @SinceMinecraftVersion("1.20.4")
    WIND_CHARGE(Version.V1_20_4),
    @SinceMinecraftVersion("1.20.4")
    BREEZE(Version.V1_20_4),
    ;
    public static final Codec<VirtualEntityType> CODEC = DefaultCodecs.createEnumCodec(VirtualEntityType.class);

    private final Version availableSinceVersion;

    VirtualEntityType(Version availableSinceVersion) {
        this.availableSinceVersion = availableSinceVersion;
    }

    public Version availableSinceVersion() {
        return availableSinceVersion;
    }
}
