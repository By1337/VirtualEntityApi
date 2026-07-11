package dev.by1337.virtualentity.core.mappings;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.VirtualEntityFactory;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.*;
import dev.by1337.virtualentity.core.virtual.animal.*;
import dev.by1337.virtualentity.core.virtual.animal.allay.VirtualAllayImpl;
import dev.by1337.virtualentity.core.virtual.animal.armadillo.VirtualArmadilloImpl;
import dev.by1337.virtualentity.core.virtual.animal.axolotl.VirtualAxolotlImpl;
import dev.by1337.virtualentity.core.virtual.animal.camel.VirtualCamelHuskImpl;
import dev.by1337.virtualentity.core.virtual.animal.camel.VirtualCamelImpl;
import dev.by1337.virtualentity.core.virtual.animal.coppergolem.VirtualCopperGolemImpl;
import dev.by1337.virtualentity.core.virtual.animal.frog.VirtualFrogImpl;
import dev.by1337.virtualentity.core.virtual.animal.frog.VirtualTadpoleImpl;
import dev.by1337.virtualentity.core.virtual.animal.goat.VirtualGoatImpl;
import dev.by1337.virtualentity.core.virtual.animal.horse.*;
import dev.by1337.virtualentity.core.virtual.animal.nautilus.VirtualNautilusImpl;
import dev.by1337.virtualentity.core.virtual.animal.nautilus.VirtualZombieNautilusImpl;
import dev.by1337.virtualentity.core.virtual.animal.sniffer.VirtualSnifferImpl;
import dev.by1337.virtualentity.core.virtual.boss.enderdragon.VirtualEndCrystalImpl;
import dev.by1337.virtualentity.core.virtual.boss.enderdragon.VirtualEnderDragonImpl;
import dev.by1337.virtualentity.core.virtual.boss.wither.VirtualWitherBossImpl;
import dev.by1337.virtualentity.core.virtual.decoration.*;
import dev.by1337.virtualentity.core.virtual.display.VirtualBlockDisplayImpl;
import dev.by1337.virtualentity.core.virtual.display.VirtualItemDisplayImpl;
import dev.by1337.virtualentity.core.virtual.display.VirtualTextDisplayImpl;
import dev.by1337.virtualentity.core.virtual.item.VirtualFallingBlockEntityImpl;
import dev.by1337.virtualentity.core.virtual.item.VirtualItemImpl;
import dev.by1337.virtualentity.core.virtual.item.VirtualPrimedTntImpl;
import dev.by1337.virtualentity.core.virtual.monster.*;
import dev.by1337.virtualentity.core.virtual.monster.breeze.VirtualBreezeImpl;
import dev.by1337.virtualentity.core.virtual.monster.creaking.VirtualCreakingImpl;
import dev.by1337.virtualentity.core.virtual.monster.creaking.VirtualCreakingTransientImpl;
import dev.by1337.virtualentity.core.virtual.monster.cubemob.VirtualSulfurCubeImpl;
import dev.by1337.virtualentity.core.virtual.monster.hoglin.VirtualHoglinImpl;
import dev.by1337.virtualentity.core.virtual.monster.piglin.VirtualPiglinBruteImpl;
import dev.by1337.virtualentity.core.virtual.monster.piglin.VirtualPiglinImpl;
import dev.by1337.virtualentity.core.virtual.npc.VirtualVillagerImpl;
import dev.by1337.virtualentity.core.virtual.npc.VirtualWanderingTraderImpl;
import dev.by1337.virtualentity.core.virtual.player.VirtualMannequinImpl;
import dev.by1337.virtualentity.core.virtual.player.VirtualPlayerImpl;
import dev.by1337.virtualentity.core.virtual.projectile.*;
import dev.by1337.virtualentity.core.virtual.projectile.windcharge.VirtualBreezeWindChargeImpl;
import dev.by1337.virtualentity.core.virtual.projectile.windcharge.VirtualWindChargeImpl;
import dev.by1337.virtualentity.core.virtual.vehicle.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;

import java.util.Set;

public class VirtualEntityRegistrar {

    public static void register() {
        VirtualEntityFactory factory = VirtualEntityApi.getFactory();

        factory.register(VirtualEntityType.ARMOR_STAND, VirtualArmorStandImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.BAT, VirtualBatImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.BEE, VirtualBeeImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.CREEPER, VirtualCreeperImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIE, VirtualZombieImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.DROWNED, VirtualDrownedImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.GUARDIAN, VirtualGuardianImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ITEM, VirtualItemImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.WITCH, VirtualWitchImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.VINDICATOR, VirtualVindicatorImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.RAVAGER, VirtualRavagerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PILLAGER, VirtualPillagerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ILLUSIONER, VirtualIllusionerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.EVOKER, VirtualEvokerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.CAT, VirtualCatImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.CHICKEN, VirtualChickenImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.COD, VirtualCodImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.COW, VirtualCowImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.DOLPHIN, VirtualDolphinImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.FOX, VirtualFoxImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.IRON_GOLEM, VirtualIronGolemImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.MOOSHROOM, VirtualMushroomCowImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.OCELOT, VirtualOcelotImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PANDA, VirtualPandaImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PARROT, VirtualParrotImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PIG, VirtualPigImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.POLAR_BEAR, VirtualPolarBearImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PUFFERFISH, VirtualPufferfishImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.RABBIT, VirtualRabbitImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SALMON, VirtualSalmonImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SHEEP, VirtualSheepImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SNOW_GOLEM, VirtualSnowGolemImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SQUID, VirtualSquidImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.TROPICAL_FISH, VirtualTropicalFishImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.TURTLE, VirtualTurtleImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.WOLF, VirtualWolfImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.DONKEY, VirtualDonkeyImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.HORSE, VirtualHorseImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.LLAMA, VirtualLlamaImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.MULE, VirtualMuleImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SKELETON_HORSE, VirtualSkeletonHorseImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.TRADER_LLAMA, VirtualTraderLlamaImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIE_HORSE, VirtualZombieHorseImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ITEM_FRAME, VirtualItemFrameImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.LEASH_KNOT, VirtualLeashFenceKnotEntityImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PAINTING, VirtualPaintingImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.FALLING_BLOCK, VirtualFallingBlockEntityImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.TNT, VirtualPrimedTntImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.BLAZE, VirtualBlazeImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.CAVE_SPIDER, VirtualCaveSpiderImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ELDER_GUARDIAN, VirtualElderGuardianImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ENDERMAN, VirtualEnderManImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ENDERMITE, VirtualEndermiteImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.GHAST, VirtualGhastImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.GIANT, VirtualGiantImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.HUSK, VirtualHuskImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SPIDER, VirtualSpiderImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SLIME, VirtualSlimeImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.MAGMA_CUBE, VirtualMagmaCubeImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PHANTOM, VirtualPhantomImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SHULKER, VirtualShulkerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SILVERFISH, VirtualSilverfishImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SKELETON, VirtualSkeletonImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.STRAY, VirtualStrayImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.STRIDER, VirtualStriderImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.VEX, VirtualVexImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ZOGLIN, VirtualZoglinImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIE_VILLAGER, VirtualZombieVillagerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIFIED_PIGLIN, VirtualZombifiedPiglinImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PIGLIN_BRUTE, VirtualPiglinBruteImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PIGLIN, VirtualPiglinImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.HOGLIN, VirtualHoglinImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.VILLAGER, VirtualVillagerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.WANDERING_TRADER, VirtualWanderingTraderImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.PLAYER, VirtualPlayerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ARROW, VirtualArrowImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.DRAGON_FIREBALL, VirtualDragonFireballImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.EVOKER_FANGS, VirtualEvokerFangsImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.EYE_OF_ENDER, VirtualEyeOfEnderImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.FIREWORK_ROCKET, VirtualFireworkRocketEntityImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.FISHING_BOBBER, VirtualFishingHookImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.FIREBALL, VirtualLargeFireballImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.LLAMA_SPIT, VirtualLlamaSpitImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SHULKER_BULLET, VirtualShulkerBulletImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SMALL_FIREBALL, VirtualSmallFireballImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SNOWBALL, VirtualSnowballImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SPECTRAL_ARROW, VirtualSpectralArrowImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.EGG, VirtualThrownEggImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ENDER_PEARL, VirtualThrownEnderpearlImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.EXPERIENCE_BOTTLE, VirtualThrownExperienceBottleImpl::new, ServerVersion.V1_16_5);

        if (Version.is1_21_4orOlder()) {
            factory.register(VirtualEntityType.POTION, VirtualThrownPotionImpl::new, ServerVersion.V1_16_5);
        } else {
            factory.register(VirtualEntityType.POTION, () -> {
                VirtualThrownSplashPotionImpl impl = new VirtualThrownSplashPotionImpl();
                impl.setItemStack(new ItemStack(Material.POTION));
                return impl;
            }, ServerVersion.V1_16_5);
        }

        factory.register(VirtualEntityType.TRIDENT, VirtualThrownTridentImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.WITHER_SKULL, VirtualWitherSkullImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.CHEST_MINECART, VirtualMinecartChestImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.COMMAND_BLOCK_MINECART, VirtualMinecartCommandBlockImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.FURNACE_MINECART, VirtualMinecartFurnaceImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.HOPPER_MINECART, VirtualMinecartHopperImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.MINECART, VirtualMinecartImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.SPAWNER_MINECART, VirtualMinecartSpawnerImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.TNT_MINECART, VirtualMinecartTNTImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.AREA_EFFECT_CLOUD, VirtualAreaEffectCloudImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.LIGHTNING_BOLT, VirtualLightningBoltImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.WITHER_SKELETON, VirtualWitherSkeletonImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.END_CRYSTAL, VirtualEndCrystalImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.ENDER_DRAGON, VirtualEnderDragonImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.WITHER, VirtualWitherBossImpl::new, ServerVersion.V1_16_5);
        factory.register(VirtualEntityType.EXPERIENCE_ORB, VirtualExperienceOrbImpl::new, ServerVersion.V1_16_5);

        factory.register(VirtualEntityType.AXOLOTL, VirtualAxolotlImpl::new, ServerVersion.V1_17_1);
        factory.register(VirtualEntityType.GOAT, VirtualGoatImpl::new, ServerVersion.V1_17_1);
        factory.register(VirtualEntityType.GLOW_ITEM_FRAME, VirtualGlowItemFrameImpl::new, ServerVersion.V1_17_1);
        factory.register(VirtualEntityType.GLOW_SQUID, VirtualGlowSquidImpl::new, ServerVersion.V1_17_1);

        factory.register(VirtualEntityType.INTERACTION, VirtualInteractionImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.BLOCK_DISPLAY, VirtualBlockDisplayImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.ITEM_DISPLAY, VirtualItemDisplayImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.TEXT_DISPLAY, VirtualTextDisplayImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.SNIFFER, VirtualSnifferImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.TADPOLE, VirtualTadpoleImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.FROG, VirtualFrogImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.CAMEL, VirtualCamelImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.ALLAY, VirtualAllayImpl::new, ServerVersion.V1_19_4);
        factory.register(VirtualEntityType.WARDEN, VirtualWardenImpl::new, ServerVersion.V1_19_4);


        factory.register(VirtualEntityType.WIND_CHARGE, VirtualWindChargeImpl::new, ServerVersion.V1_20_4);
        factory.register(VirtualEntityType.BREEZE, VirtualBreezeImpl::new, ServerVersion.V1_20_4);

        factory.register(VirtualEntityType.OMINOUS_ITEM_SPAWNER, VirtualOminousItemSpawnerImpl::new, ServerVersion.V1_20_6);
        factory.register(VirtualEntityType.BREEZE_WIND_CHARGE, VirtualBreezeWindChargeImpl::new, ServerVersion.V1_20_6);
        factory.register(VirtualEntityType.BOGGED, VirtualBoggedImpl::new, ServerVersion.V1_20_6);
        factory.register(VirtualEntityType.ARMADILLO, VirtualArmadilloImpl::new, ServerVersion.V1_20_6);


        factory.register(VirtualEntityType.BAMBOO_CHEST_RAFT, VirtualChestRaftImpl::new, ServerVersion.V1_21_3);
        factory.register(VirtualEntityType.BAMBOO_RAFT, VirtualRaftImpl::new, ServerVersion.V1_21_3);

        if (Version.VERSION.newerThanOrEqual(Version.V1_21_3)) {
            factory.register(VirtualEntityType.CHEST_BOAT, () -> new VirtualChestBoatImpl(VirtualEntityType.OAK_CHEST_BOAT), ServerVersion.V1_19_4);
            factory.register(VirtualEntityType.BOAT, () -> new VirtualBoatImpl(VirtualEntityType.OAK_BOAT), ServerVersion.V1_16_5);
        } else {
            factory.register(VirtualEntityType.CHEST_BOAT, () -> new VirtualChestBoatImpl(VirtualEntityType.CHEST_BOAT), ServerVersion.V1_19_4);
            factory.register(VirtualEntityType.BOAT, () -> new VirtualBoatImpl(VirtualEntityType.BOAT), ServerVersion.V1_16_5);
        }
        registerBoats();
        factory.register(VirtualEntityType.CREAKING, VirtualCreakingImpl::new, ServerVersion.V1_21_3);
        factory.register(VirtualEntityType.CREAKING_TRANSIENT, VirtualCreakingTransientImpl::new, ServerVersion.V1_21_3); // removed in 1.21.4

        if (Version.is1_21_5orNewer()) {
            factory.register(VirtualEntityType.LINGERING_POTION, VirtualThrownLingeringPotionImpl::new, ServerVersion.V1_21_5);
            factory.register(VirtualEntityType.SPLASH_POTION, VirtualThrownSplashPotionImpl::new, ServerVersion.V1_21_5);
        } else {
            factory.register(VirtualEntityType.LINGERING_POTION, VirtualThrownPotionImpl::new, ServerVersion.V1_21_5); // todo хз
            factory.register(VirtualEntityType.SPLASH_POTION, VirtualThrownPotionImpl::new, ServerVersion.V1_21_5);
        }
        factory.register(VirtualEntityType.HAPPY_GHAST, VirtualHappyGhastImpl::new, ServerVersion.V1_21_6);

        factory.register(VirtualEntityType.MANNEQUIN, VirtualMannequinImpl::new, ServerVersion.V1_21_9);
        factory.register(VirtualEntityType.COPPER_GOLEM, VirtualCopperGolemImpl::new, ServerVersion.V1_21_9);
        factory.register(VirtualEntityType.CAMEL_HUSK, VirtualCamelHuskImpl::new, ServerVersion.V1_21_11);
        factory.register(VirtualEntityType.NAUTILUS, VirtualNautilusImpl::new, ServerVersion.V1_21_11);
        factory.register(VirtualEntityType.PARCHED, VirtualParchedImpl::new, ServerVersion.V1_21_11);
        factory.register(VirtualEntityType.ZOMBIE_NAUTILUS, VirtualZombieNautilusImpl::new, ServerVersion.V1_21_11);
        factory.register(VirtualEntityType.SULFUR_CUBE, VirtualSulfurCubeImpl::new, ServerVersion.V26_2);
    }

    private static void registerBoats() {
        Set<VirtualEntityType> boats = Set.of(
                VirtualEntityType.ACACIA_BOAT,
                VirtualEntityType.BIRCH_BOAT,
                VirtualEntityType.CHERRY_BOAT,
                VirtualEntityType.DARK_OAK_BOAT,
                VirtualEntityType.JUNGLE_BOAT,
                VirtualEntityType.MANGROVE_BOAT,
                VirtualEntityType.OAK_BOAT,
                VirtualEntityType.PALE_OAK_BOAT,
                VirtualEntityType.SPRUCE_BOAT
        );
        Set<VirtualEntityType> chestBoats = Set.of(
                VirtualEntityType.ACACIA_CHEST_BOAT,
                VirtualEntityType.BIRCH_CHEST_BOAT,
                VirtualEntityType.CHERRY_CHEST_BOAT,
                VirtualEntityType.DARK_OAK_CHEST_BOAT,
                VirtualEntityType.JUNGLE_CHEST_BOAT,
                VirtualEntityType.MANGROVE_CHEST_BOAT,
                VirtualEntityType.OAK_CHEST_BOAT,
                VirtualEntityType.PALE_OAK_CHEST_BOAT,
                VirtualEntityType.SPRUCE_CHEST_BOAT
        );
        VirtualEntityFactory factory = VirtualEntityApi.getFactory();
        for (VirtualEntityType boat : boats) {
            factory.register(boat, () -> new VirtualBoatImpl(boat), ServerVersion.V1_21_3);
        }
        for (VirtualEntityType chestBoat : chestBoats) {
            factory.register(chestBoat, () -> new VirtualChestBoatImpl(chestBoat), ServerVersion.V1_21_3);
        }
    }
}
