package dev.by1337.virtualentity.api.entity;

import blib.com.mojang.serialization.Codec;
import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import org.by1337.blib.configuration.serialization.DefaultCodecs;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

public enum VirtualEntityType {
    AREA_EFFECT_CLOUD(ServerVersion.V1_16_5),
    ARMOR_STAND(ServerVersion.V1_16_5),
    ARROW(ServerVersion.V1_16_5),
    BAT(ServerVersion.V1_16_5),
    BEE(ServerVersion.V1_16_5),
    BLAZE(ServerVersion.V1_16_5),
    @RemovedInMinecraftVersion("1.20.3")
    BOAT(ServerVersion.V1_16_5, ServerVersion.V1_21_3),
    CAT(ServerVersion.V1_16_5),
    CAVE_SPIDER(ServerVersion.V1_16_5),
    CHICKEN(ServerVersion.V1_16_5),
    COD(ServerVersion.V1_16_5),
    COW(ServerVersion.V1_16_5),
    CREEPER(ServerVersion.V1_16_5),
    DOLPHIN(ServerVersion.V1_16_5),
    DONKEY(ServerVersion.V1_16_5),
    DRAGON_FIREBALL(ServerVersion.V1_16_5),
    DROWNED(ServerVersion.V1_16_5),
    ELDER_GUARDIAN(ServerVersion.V1_16_5),
    END_CRYSTAL(ServerVersion.V1_16_5),
    ENDER_DRAGON(ServerVersion.V1_16_5),
    ENDERMAN(ServerVersion.V1_16_5),
    ENDERMITE(ServerVersion.V1_16_5),
    EVOKER(ServerVersion.V1_16_5),
    EVOKER_FANGS(ServerVersion.V1_16_5),
    EXPERIENCE_ORB(ServerVersion.V1_16_5),
    EYE_OF_ENDER(ServerVersion.V1_16_5),
    FALLING_BLOCK(ServerVersion.V1_16_5),
    FIREWORK_ROCKET(ServerVersion.V1_16_5),
    FOX(ServerVersion.V1_16_5),
    GHAST(ServerVersion.V1_16_5),
    GIANT(ServerVersion.V1_16_5),
    GUARDIAN(ServerVersion.V1_16_5),
    HOGLIN(ServerVersion.V1_16_5),
    HORSE(ServerVersion.V1_16_5),
    HUSK(ServerVersion.V1_16_5),
    ILLUSIONER(ServerVersion.V1_16_5),
    IRON_GOLEM(ServerVersion.V1_16_5),
    ITEM(ServerVersion.V1_16_5),
    ITEM_FRAME(ServerVersion.V1_16_5),
    FIREBALL(ServerVersion.V1_16_5),
    LEASH_KNOT(ServerVersion.V1_16_5),
    LIGHTNING_BOLT(ServerVersion.V1_16_5),
    LLAMA(ServerVersion.V1_16_5),
    LLAMA_SPIT(ServerVersion.V1_16_5),
    MAGMA_CUBE(ServerVersion.V1_16_5),
    MINECART(ServerVersion.V1_16_5),
    CHEST_MINECART(ServerVersion.V1_16_5),
    COMMAND_BLOCK_MINECART(ServerVersion.V1_16_5),
    FURNACE_MINECART(ServerVersion.V1_16_5),
    HOPPER_MINECART(ServerVersion.V1_16_5),
    SPAWNER_MINECART(ServerVersion.V1_16_5),
    TNT_MINECART(ServerVersion.V1_16_5),
    MULE(ServerVersion.V1_16_5),
    MOOSHROOM(ServerVersion.V1_16_5),
    OCELOT(ServerVersion.V1_16_5),
    PAINTING(ServerVersion.V1_16_5),
    PANDA(ServerVersion.V1_16_5),
    PARROT(ServerVersion.V1_16_5),
    PHANTOM(ServerVersion.V1_16_5),
    PIG(ServerVersion.V1_16_5),
    PIGLIN(ServerVersion.V1_16_5),
    PIGLIN_BRUTE(ServerVersion.V1_16_5),
    PILLAGER(ServerVersion.V1_16_5),
    POLAR_BEAR(ServerVersion.V1_16_5),
    TNT(ServerVersion.V1_16_5),
    PUFFERFISH(ServerVersion.V1_16_5),
    RABBIT(ServerVersion.V1_16_5),
    RAVAGER(ServerVersion.V1_16_5),
    SALMON(ServerVersion.V1_16_5),
    SHEEP(ServerVersion.V1_16_5),
    SHULKER(ServerVersion.V1_16_5),
    SHULKER_BULLET(ServerVersion.V1_16_5),
    SILVERFISH(ServerVersion.V1_16_5),
    SKELETON(ServerVersion.V1_16_5),
    SKELETON_HORSE(ServerVersion.V1_16_5),
    SLIME(ServerVersion.V1_16_5),
    SMALL_FIREBALL(ServerVersion.V1_16_5),
    SNOW_GOLEM(ServerVersion.V1_16_5),
    SNOWBALL(ServerVersion.V1_16_5),
    SPECTRAL_ARROW(ServerVersion.V1_16_5),
    SPIDER(ServerVersion.V1_16_5),
    SQUID(ServerVersion.V1_16_5),
    STRAY(ServerVersion.V1_16_5),
    STRIDER(ServerVersion.V1_16_5),
    EGG(ServerVersion.V1_16_5),
    ENDER_PEARL(ServerVersion.V1_16_5),
    EXPERIENCE_BOTTLE(ServerVersion.V1_16_5),
    @RemovedInMinecraftVersion("1.21.5")
    POTION(ServerVersion.V1_16_5, ServerVersion.V1_21_5),
    TRIDENT(ServerVersion.V1_16_5),
    TRADER_LLAMA(ServerVersion.V1_16_5),
    TROPICAL_FISH(ServerVersion.V1_16_5),
    TURTLE(ServerVersion.V1_16_5),
    VEX(ServerVersion.V1_16_5),
    VILLAGER(ServerVersion.V1_16_5),
    VINDICATOR(ServerVersion.V1_16_5),
    WANDERING_TRADER(ServerVersion.V1_16_5),
    WITCH(ServerVersion.V1_16_5),
    WITHER(ServerVersion.V1_16_5),
    WITHER_SKELETON(ServerVersion.V1_16_5),
    WITHER_SKULL(ServerVersion.V1_16_5),
    WOLF(ServerVersion.V1_16_5),
    ZOGLIN(ServerVersion.V1_16_5),
    ZOMBIE(ServerVersion.V1_16_5),
    ZOMBIE_HORSE(ServerVersion.V1_16_5),
    ZOMBIE_VILLAGER(ServerVersion.V1_16_5),
    ZOMBIFIED_PIGLIN(ServerVersion.V1_16_5),
    PLAYER(ServerVersion.V1_16_5),
    FISHING_BOBBER(ServerVersion.V1_16_5),
    @SinceMinecraftVersion("1.17.1")
    AXOLOTL(ServerVersion.V1_17_1),
    @SinceMinecraftVersion("1.17.1")
    GOAT(ServerVersion.V1_17_1),
    @SinceMinecraftVersion("1.17.1")
    GLOW_ITEM_FRAME(ServerVersion.V1_17_1),
    @SinceMinecraftVersion("1.17.1")
    GLOW_SQUID(ServerVersion.V1_17_1),

    @SinceMinecraftVersion("1.19.4")
    WARDEN(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    TEXT_DISPLAY(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    CAMEL(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    SNIFFER(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    ITEM_DISPLAY(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    FROG(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    BLOCK_DISPLAY(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    @RemovedInMinecraftVersion("1.20.3")
    CHEST_BOAT(ServerVersion.V1_19_4, ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.19.4")
    INTERACTION(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    ALLAY(ServerVersion.V1_19_4),
    @SinceMinecraftVersion("1.19.4")
    TADPOLE(ServerVersion.V1_19_4),

    @SinceMinecraftVersion("1.20.4")
    WIND_CHARGE(ServerVersion.V1_20_4), // can only be created with 1.21
    @SinceMinecraftVersion("1.20.4")
    BREEZE(ServerVersion.V1_20_4), // can only be created with 1.21

    @SinceMinecraftVersion("1.20.6")
    OMINOUS_ITEM_SPAWNER(ServerVersion.V1_20_6), // can only be created with 1.21
    @SinceMinecraftVersion("1.20.6")
    BOGGED(ServerVersion.V1_20_6), // can only be created with 1.21
    @SinceMinecraftVersion("1.20.6")
    BREEZE_WIND_CHARGE(ServerVersion.V1_20_6), // can only be created with 1.21
    @SinceMinecraftVersion("1.20.6")
    ARMADILLO(ServerVersion.V1_20_6),

    @SinceMinecraftVersion("1.21.3")
    ACACIA_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    ACACIA_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    BAMBOO_CHEST_RAFT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    BAMBOO_RAFT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    BIRCH_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    BIRCH_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    CHERRY_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    CHERRY_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    CREAKING(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    @RemovedInMinecraftVersion("1.21.4")
    CREAKING_TRANSIENT(ServerVersion.V1_21_3, ServerVersion.V1_21_4),
    @SinceMinecraftVersion("1.21.3")
    DARK_OAK_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    DARK_OAK_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    JUNGLE_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    JUNGLE_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    MANGROVE_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    MANGROVE_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    OAK_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    OAK_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    PALE_OAK_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    PALE_OAK_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    SPRUCE_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.3")
    SPRUCE_CHEST_BOAT(ServerVersion.V1_21_3),
    @SinceMinecraftVersion("1.21.5")
    LINGERING_POTION(ServerVersion.V1_21_5),
    @SinceMinecraftVersion("1.21.5")
    SPLASH_POTION(ServerVersion.V1_21_5),
    @SinceMinecraftVersion("1.21.6")
    HAPPY_GHAST(ServerVersion.V1_21_6),
    @SinceMinecraftVersion("1.21.9")
    MANNEQUIN(ServerVersion.V1_21_9),
    @SinceMinecraftVersion("1.21.9")
    COPPER_GOLEM(ServerVersion.V1_21_9),
    @SinceMinecraftVersion("1.21.11")
    CAMEL_HUSK(ServerVersion.V1_21_11),
    @SinceMinecraftVersion("1.21.11")
    NAUTILUS(ServerVersion.V1_21_11),
    @SinceMinecraftVersion("1.21.11")
    PARCHED(ServerVersion.V1_21_11),
    @SinceMinecraftVersion("1.21.11")
    ZOMBIE_NAUTILUS(ServerVersion.V1_21_11),
    @SinceMinecraftVersion("26.2")
    SULFUR_CUBE(ServerVersion.V26_2),

    ;
    public static final Codec<VirtualEntityType> CODEC = DefaultCodecs.createEnumCodec(VirtualEntityType.class);

    private final int availableSinceVersion;
    private final int removedIn;

    VirtualEntityType(int availableSinceVersion) {
        this.availableSinceVersion = availableSinceVersion;
        removedIn = -1;
    }

    VirtualEntityType(int availableSinceVersion, int removedIn) {
        this.availableSinceVersion = availableSinceVersion;
        this.removedIn = removedIn;
    }

    public int availableSinceVersion() {
        return availableSinceVersion;
    }
    
    public int removedIn() {
        return removedIn;
    }
}
