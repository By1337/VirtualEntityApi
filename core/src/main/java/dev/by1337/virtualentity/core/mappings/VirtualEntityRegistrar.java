package dev.by1337.virtualentity.core.mappings;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.VirtualEntityFactory;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.virtual.*;
import dev.by1337.virtualentity.core.virtual.decoration.*;
import dev.by1337.virtualentity.core.virtual.animal.*;
import dev.by1337.virtualentity.core.virtual.animal.horse.*;
import dev.by1337.virtualentity.core.virtual.decoration.VirtualArmorStandImpl;
import dev.by1337.virtualentity.core.virtual.item.*;
import dev.by1337.virtualentity.core.virtual.monster.*;
import dev.by1337.virtualentity.core.virtual.monster.piglin.*;
import dev.by1337.virtualentity.core.virtual.monster.hoglin.*;
import dev.by1337.virtualentity.core.virtual.npc.*;
import dev.by1337.virtualentity.core.virtual.player.*;
import dev.by1337.virtualentity.core.virtual.projectile.*;
import dev.by1337.virtualentity.core.virtual.vehicle.*;
import dev.by1337.virtualentity.core.virtual.boss.enderdragon.*;
import dev.by1337.virtualentity.core.virtual.boss.wither.*;
import org.by1337.blib.util.Version;

public class VirtualEntityRegistrar {

    public static void register(){
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
        factory.register(VirtualEntityType.POTION, VirtualThrownPotionImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.TRIDENT, VirtualThrownTridentImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.WITHER_SKULL, VirtualWitherSkullImpl::new, Version.V1_16_5);
        factory.register(VirtualEntityType.BOAT, VirtualBoatImpl::new, Version.V1_16_5);
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
    }
}
