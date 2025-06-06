package dev.by1337.virtualentity.core.mappings;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.VirtualEntityFactory;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.*;
import dev.by1337.virtualentity.core.virtual.animal.*;
import dev.by1337.virtualentity.core.virtual.animal.allay.VirtualAllayImpl;
import dev.by1337.virtualentity.core.virtual.animal.armadillo.VirtualArmadilloImpl;
import dev.by1337.virtualentity.core.virtual.animal.axolotl.VirtualAxolotlImpl;
import dev.by1337.virtualentity.core.virtual.animal.camel.VirtualCamelImpl;
import dev.by1337.virtualentity.core.virtual.animal.frog.VirtualFrogImpl;
import dev.by1337.virtualentity.core.virtual.animal.frog.VirtualTadpoleImpl;
import dev.by1337.virtualentity.core.virtual.animal.goat.VirtualGoatImpl;
import dev.by1337.virtualentity.core.virtual.animal.horse.*;
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
import dev.by1337.virtualentity.core.virtual.monster.hoglin.VirtualHoglinImpl;
import dev.by1337.virtualentity.core.virtual.monster.piglin.VirtualPiglinBruteImpl;
import dev.by1337.virtualentity.core.virtual.monster.piglin.VirtualPiglinImpl;
import dev.by1337.virtualentity.core.virtual.npc.VirtualVillagerImpl;
import dev.by1337.virtualentity.core.virtual.npc.VirtualWanderingTraderImpl;
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

        factory.register(VirtualEntityType.ARMOR_STAND, VirtualArmorStandImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.BAT, VirtualBatImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.BEE, VirtualBeeImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.CREEPER, VirtualCreeperImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIE, VirtualZombieImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.DROWNED, VirtualDrownedImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.GUARDIAN, VirtualGuardianImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ITEM, VirtualItemImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WITCH, VirtualWitchImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.VINDICATOR, VirtualVindicatorImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.RAVAGER, VirtualRavagerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PILLAGER, VirtualPillagerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ILLUSIONER, VirtualIllusionerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.EVOKER, VirtualEvokerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.CAT, VirtualCatImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.CHICKEN, VirtualChickenImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.COD, VirtualCodImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.COW, VirtualCowImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.DOLPHIN, VirtualDolphinImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.FOX, VirtualFoxImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.IRON_GOLEM, VirtualIronGolemImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.MOOSHROOM, VirtualMushroomCowImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.OCELOT, VirtualOcelotImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PANDA, VirtualPandaImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PARROT, VirtualParrotImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PIG, VirtualPigImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.POLAR_BEAR, VirtualPolarBearImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PUFFERFISH, VirtualPufferfishImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.RABBIT, VirtualRabbitImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SALMON, VirtualSalmonImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SHEEP, VirtualSheepImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SNOW_GOLEM, VirtualSnowGolemImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SQUID, VirtualSquidImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.TROPICAL_FISH, VirtualTropicalFishImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.TURTLE, VirtualTurtleImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WOLF, VirtualWolfImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.DONKEY, VirtualDonkeyImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.HORSE, VirtualHorseImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.LLAMA, VirtualLlamaImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.MULE, VirtualMuleImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SKELETON_HORSE, VirtualSkeletonHorseImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.TRADER_LLAMA, VirtualTraderLlamaImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIE_HORSE, VirtualZombieHorseImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ITEM_FRAME, VirtualItemFrameImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.LEASH_KNOT, VirtualLeashFenceKnotEntityImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PAINTING, VirtualPaintingImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.FALLING_BLOCK, VirtualFallingBlockEntityImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.TNT, VirtualPrimedTntImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.BLAZE, VirtualBlazeImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.CAVE_SPIDER, VirtualCaveSpiderImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ELDER_GUARDIAN, VirtualElderGuardianImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ENDERMAN, VirtualEnderManImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ENDERMITE, VirtualEndermiteImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.GHAST, VirtualGhastImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.GIANT, VirtualGiantImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.HUSK, VirtualHuskImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SPIDER, VirtualSpiderImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SLIME, VirtualSlimeImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.MAGMA_CUBE, VirtualMagmaCubeImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PHANTOM, VirtualPhantomImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SHULKER, VirtualShulkerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SILVERFISH, VirtualSilverfishImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SKELETON, VirtualSkeletonImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.STRAY, VirtualStrayImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.STRIDER, VirtualStriderImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.VEX, VirtualVexImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ZOGLIN, VirtualZoglinImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIE_VILLAGER, VirtualZombieVillagerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ZOMBIFIED_PIGLIN, VirtualZombifiedPiglinImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PIGLIN_BRUTE, VirtualPiglinBruteImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PIGLIN, VirtualPiglinImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.HOGLIN, VirtualHoglinImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.VILLAGER, VirtualVillagerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WANDERING_TRADER, VirtualWanderingTraderImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.PLAYER, VirtualPlayerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ARROW, VirtualArrowImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.DRAGON_FIREBALL, VirtualDragonFireballImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.EVOKER_FANGS, VirtualEvokerFangsImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.EYE_OF_ENDER, VirtualEyeOfEnderImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.FIREWORK_ROCKET, VirtualFireworkRocketEntityImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.FISHING_BOBBER, VirtualFishingHookImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.FIREBALL, VirtualLargeFireballImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.LLAMA_SPIT, VirtualLlamaSpitImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SHULKER_BULLET, VirtualShulkerBulletImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SMALL_FIREBALL, VirtualSmallFireballImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SNOWBALL, VirtualSnowballImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SPECTRAL_ARROW, VirtualSpectralArrowImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.EGG, VirtualThrownEggImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ENDER_PEARL, VirtualThrownEnderpearlImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.EXPERIENCE_BOTTLE, VirtualThrownExperienceBottleImpl::new, Version.V1_16_5);

        if (Version.is1_21_4orOlder()) {
            factory.register(VirtualEntityType.POTION, VirtualThrownPotionImpl::new, Version.V1_16_5);
        } else {
            factory.register(VirtualEntityType.POTION, () -> { // todo здесь надо вернуть VirtualThrownPotion
                VirtualThrownSplashPotionImpl impl = new VirtualThrownSplashPotionImpl();
                impl.setItemStack(new ItemStack(Material.POTION));
                return impl;
            }, Version.V1_16_5);
        }

        factory.register(VirtualEntityType.TRIDENT, VirtualThrownTridentImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WITHER_SKULL, VirtualWitherSkullImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.CHEST_MINECART, VirtualMinecartChestImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.COMMAND_BLOCK_MINECART, VirtualMinecartCommandBlockImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.FURNACE_MINECART, VirtualMinecartFurnaceImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.HOPPER_MINECART, VirtualMinecartHopperImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.MINECART, VirtualMinecartImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.SPAWNER_MINECART, VirtualMinecartSpawnerImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.TNT_MINECART, VirtualMinecartTNTImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.AREA_EFFECT_CLOUD, VirtualAreaEffectCloudImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.LIGHTNING_BOLT, VirtualLightningBoltImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WITHER_SKELETON, VirtualWitherSkeletonImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.END_CRYSTAL, VirtualEndCrystalImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.ENDER_DRAGON, VirtualEnderDragonImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WITHER, VirtualWitherBossImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.EXPERIENCE_ORB, VirtualExperienceOrbImpl::new, Version.V1_16_5);

        factory.register(VirtualEntityType.AXOLOTL, VirtualAxolotlImpl::new, Version.V1_17_1);
        factory.register(VirtualEntityType.GOAT, VirtualGoatImpl::new, Version.V1_17_1);
        factory.register(VirtualEntityType.GLOW_ITEM_FRAME, VirtualGlowItemFrameImpl::new, Version.V1_17_1);
        factory.register(VirtualEntityType.GLOW_SQUID, VirtualGlowSquidImpl::new, Version.V1_17_1);

        factory.register(VirtualEntityType.INTERACTION, VirtualInteractionImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.BLOCK_DISPLAY, VirtualBlockDisplayImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.ITEM_DISPLAY, VirtualItemDisplayImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.TEXT_DISPLAY, VirtualTextDisplayImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.SNIFFER, VirtualSnifferImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.TADPOLE, VirtualTadpoleImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.FROG, VirtualFrogImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.CAMEL, VirtualCamelImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.ALLAY, VirtualAllayImpl::new, Version.V1_19_4);
        factory.register(VirtualEntityType.WARDEN, VirtualWardenImpl::new, Version.V1_19_4);


        factory.register(VirtualEntityType.WIND_CHARGE, VirtualWindChargeImpl::new, Version.V1_20_4);
        factory.register(VirtualEntityType.BREEZE, VirtualBreezeImpl::new, Version.V1_20_4);

        factory.register(VirtualEntityType.OMINOUS_ITEM_SPAWNER, VirtualOminousItemSpawnerImpl::new, Version.V1_20_6);
        factory.register(VirtualEntityType.BREEZE_WIND_CHARGE, VirtualBreezeWindChargeImpl::new, Version.V1_20_6);
        factory.register(VirtualEntityType.BOGGED, VirtualBoggedImpl::new, Version.V1_20_6);
        factory.register(VirtualEntityType.ARMADILLO, VirtualArmadilloImpl::new, Version.V1_20_6);


        factory.register(VirtualEntityType.BAMBOO_CHEST_RAFT, VirtualChestRaftImpl::new, Version.V1_21_3);
        factory.register(VirtualEntityType.BAMBOO_RAFT, VirtualRaftImpl::new, Version.V1_21_3);

        if (Version.VERSION.newerThanOrEqual(Version.V1_21_3)) {
            factory.register(VirtualEntityType.CHEST_BOAT, () -> new VirtualChestBoatImpl(VirtualEntityType.OAK_CHEST_BOAT), Version.V1_19_4);
            factory.register(VirtualEntityType.BOAT, () -> new VirtualBoatImpl(VirtualEntityType.OAK_BOAT), Version.V1_16_5);
        } else {
            factory.register(VirtualEntityType.CHEST_BOAT, () -> new VirtualChestBoatImpl(VirtualEntityType.CHEST_BOAT), Version.V1_19_4);
            factory.register(VirtualEntityType.BOAT, () -> new VirtualBoatImpl(VirtualEntityType.BOAT), Version.V1_16_5);
        }
        registerBoats();
        factory.register(VirtualEntityType.CREAKING, VirtualCreakingImpl::new, Version.V1_21_3);
        factory.register(VirtualEntityType.CREAKING_TRANSIENT, VirtualCreakingTransientImpl::new, Version.V1_21_3); // removed in 1.21.4

        if (Version.is1_21_5orNewer()) {
            factory.register(VirtualEntityType.LINGERING_POTION, VirtualThrownLingeringPotionImpl::new, Version.V1_21_5);
            factory.register(VirtualEntityType.SPLASH_POTION, VirtualThrownSplashPotionImpl::new, Version.V1_21_5);
        } else {
            factory.register(VirtualEntityType.LINGERING_POTION, VirtualThrownPotionImpl::new, Version.V1_21_5); // todo хз
            factory.register(VirtualEntityType.SPLASH_POTION, VirtualThrownPotionImpl::new, Version.V1_21_5);
        }

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
            factory.register(boat, () -> new VirtualBoatImpl(boat), Version.V1_21_3);
        }
        for (VirtualEntityType chestBoat : chestBoats) {
            factory.register(chestBoat, () -> new VirtualChestBoatImpl(chestBoat), Version.V1_21_3);
        }
    }
}
