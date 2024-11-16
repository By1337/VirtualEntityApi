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
    }
}
