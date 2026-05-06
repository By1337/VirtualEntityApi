package dev.by1337.virtualentity.dumper;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.entity.animal.golem.CopperGolemState;
import net.minecraft.world.entity.animal.panda.Panda;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.entity.monster.illager.SpellcasterIllager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import org.by1337.blib.nbt.MojangNbtReader;
import org.by1337.blib.nbt.NBT;
import org.by1337.blib.nbt.NBTToStringStyle;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.nbt.impl.ListNBT;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MappingsCreator {

    public static void create(Path dataFolder) throws Throwable {
        CompoundTag data = new CompoundTag();

        Set<Class<?>> entities = new HashSet<>();
        // find all entities class
        for (Field field : EntityType.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != EntityType.class) continue;
            Class<?> entityClazz = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());

            entities.add(entityClazz);
            Class<?> superClazz = entityClazz.getSuperclass();
            do {
                entities.add(superClazz);
                superClazz = superClazz.getSuperclass();
            } while (superClazz != Object.class);
        }
        Map<EntityDataSerializer<?>, String> serializers = getEntityDataSerializers();

        CompoundTag entitiesData = new CompoundTag();
        for (Class<?> entityType : entities) {
            List<NBT> netwokData = new ArrayList<>();
            for (Field field : entityType.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType() != EntityDataAccessor.class) continue;

                EntityDataAccessor<?> accessor = (EntityDataAccessor<?>) field.get(null);
                CompoundTag o = new CompoundTag();
                o.putString("type", serializers.getOrDefault(accessor.serializer(), "UNKNOWN"));
                o.putInt("id", accessor.id());
                o.putString("name", field.getName());
                netwokData.add(o);
            }
            if (!netwokData.isEmpty()) {
                String name = entityType.getCanonicalName();
                String trimName = name.substring(name.lastIndexOf(".") + 1);
                ListNBT listNBT = new ListNBT(netwokData);
                if (entitiesData.has(trimName)) {
                    throw new IllegalStateException("Dublicate entity type " + trimName + " full name " + entityType.getCanonicalName());
                }
                entitiesData.putTag(trimName, listNBT);
            }
        }
        data.putTag("entities", entitiesData);

        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        CompoundTag typeToData = new CompoundTag();

        for (Field field : EntityType.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != EntityType.class) continue;
            EntityType<?> type = (EntityType<?>) field.get(null);
            if (type == EntityType.MARKER) continue;
            CompoundTag info = new CompoundTag();
            info.putInt("networkId", BuiltInRegistries.ENTITY_TYPE.getId(type));
            info.putString("spawnPacket", "ADD_ENTITY_PACKET");
            typeToData.putTag(field.getName(), info);
        }
        data.putTag("typeToData", typeToData);

        CompoundTag serializerToId = new CompoundTag();
        for (Field field : EntityDataSerializers.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != EntityDataSerializer.class) continue;
            serializerToId.putInt(field.getName(), EntityDataSerializers.getSerializedId((EntityDataSerializer<?>) field.get(null)));
        }
        data.putTag("serializerToId", serializerToId);

        CompoundTag enums = new CompoundTag();

//        { // BoatType replaced in 1.20.3 by the specific types ACACIA_BOAT, BIRCH_BOAT, CHERRY_BOAT...
//            CompoundTag boatType = new CompoundTag();
//            for (Boat.Type value : Boat.Type.values()) {
//                boatType.putInt(value.name(), value.ordinal());
//            }
//            enums.putTag("dev.by1337.virtualentity.api.entity.BoatType", boatType);
//        }
        { // DyeColor
            CompoundTag dyeColor = new CompoundTag();
            for (var value : DyeColor.values()) {
                dyeColor.putInt(value.name(), value.getId());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.DyeColor", dyeColor);
        }
        { // EnderDragonPhase
            CompoundTag phase = new CompoundTag();
            for (Field field : EnderDragonPhase.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType() != EnderDragonPhase.class) continue;
                EnderDragonPhase<?> phase1 = (EnderDragonPhase<?>) field.get(null);
                phase.putInt(field.getName(), phase1.getId());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.EnderDragonPhase", phase);
        }

        { // animationToId
            CompoundTag animationToId = new CompoundTag();
            animationToId.putInt("SWING_MAIN_ARM", ClientboundAnimatePacket.SWING_MAIN_HAND);
            //animationToId.putInt("TAKE_DAMAGE", 1); // removed
            animationToId.putInt("LEAVE_BED", ClientboundAnimatePacket.WAKE_UP);
            animationToId.putInt("SWING_OFFHAND", ClientboundAnimatePacket.SWING_OFF_HAND);
            animationToId.putInt("CRITICAL_EFFECT", ClientboundAnimatePacket.CRITICAL_HIT);
            animationToId.putInt("MAGIC_CRITICAL_EFFECT", ClientboundAnimatePacket.MAGIC_CRITICAL_HIT);
            enums.putTag("dev.by1337.virtualentity.api.entity.EntityAnimation", animationToId);
        }

        { // EquipmentSlot
            CompoundTag equipmentSlotToId = new CompoundTag();
            for (var value : EquipmentSlot.values()) {
                equipmentSlotToId.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.EquipmentSlot", equipmentSlotToId);
        }

        { // FoxType
            CompoundTag foxTypeToId = new CompoundTag();
            for (var value : Fox.Variant.values()) {
                foxTypeToId.putInt(value.name(), value.getId());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.FoxType", foxTypeToId);
        }
        { // IllagerSpell
            CompoundTag illagerSpellToId = new CompoundTag();
            for (var value : SpellcasterIllager.IllagerSpell.values()) {
                illagerSpellToId.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.IllagerSpell", illagerSpellToId);
        }
        { // InteractionHand
            CompoundTag hand = new CompoundTag();
            for (var value : InteractionHand.values()) {
                hand.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.InteractionHand", hand);
        }
        { // PandaGene
            CompoundTag pandaGeneToId = new CompoundTag();
            for (var value : Panda.Gene.values()) {
                pandaGeneToId.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.PandaGene", pandaGeneToId);
        }
        { // Pose
            CompoundTag poseToId = new CompoundTag();
            for (var value : Pose.values()) {
                poseToId.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.Pose", poseToId);
        }
        { // ItemDisplayType
            CompoundTag poseToId = new CompoundTag();
            for (var value : ItemDisplayContext.values()) {
                poseToId.putInt(value.name(), value.getId());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.ItemDisplayType", poseToId);
        }
        { // AxolotVariant
            CompoundTag poseToId = new CompoundTag();
            for (var value : Axolotl.Variant.values()) {
                poseToId.putInt(value.name(), value.getId());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.AxolotVariant", poseToId);
        }
        { // SnifferState
            CompoundTag poseToId = new CompoundTag();
            for (var value : Sniffer.State.values()) {
                poseToId.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.SnifferState", poseToId);
        }

        { // VillagerProfession
            CompoundTag profession = new CompoundTag();
            for (VillagerProfession type : BuiltInRegistries.VILLAGER_PROFESSION) {
                String p = BuiltInRegistries.VILLAGER_PROFESSION.getKey(type).getPath();
                profession.putInt(p.toUpperCase(), BuiltInRegistries.VILLAGER_PROFESSION.getId(type));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.npc.VillagerProfession", profession);
        }
        { // VillagerType
            CompoundTag villagerType = new CompoundTag();
            for (VillagerType type : BuiltInRegistries.VILLAGER_TYPE) {
                String p = BuiltInRegistries.VILLAGER_TYPE.getKey(type).getPath();
                villagerType.putInt(p.toUpperCase(), BuiltInRegistries.VILLAGER_TYPE.getId(type));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.npc.VillagerType", villagerType);
        }
        { // PaintingMotive
            var idMap = MinecraftServer.getServer().registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT).asHolderIdMap();
            CompoundTag paiting = new CompoundTag();
            for (Holder<PaintingVariant> holder : idMap) {
                paiting.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.PaintingMotive", paiting);
        }
        var registryAccess = MinecraftServer.getServer().registryAccess();

        { // CatVariant
            CompoundTag cat = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.CAT_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                cat.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CatVariant", cat);
        }
        { // FrogVariant
            CompoundTag frog = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.FROG_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                frog.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.FrogVariant", frog);
        }

        { // WolfVariant
            var idMap = MinecraftServer.getServer().registryAccess().lookupOrThrow(Registries.WOLF_VARIANT).asHolderIdMap();
            CompoundTag wolf = new CompoundTag();
            for (var holder : idMap) {
                wolf.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.WolfVariant", wolf);
        }
        { // BillboardConstraints
            CompoundTag billboardConstraints = new CompoundTag();
            for (var value : Display.BillboardConstraints.values()) {
                billboardConstraints.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.BillboardConstraints", billboardConstraints);
        }
        { // ArmadilloState
            CompoundTag billboardConstraints = new CompoundTag();
            for (var value : Armadillo.ArmadilloState.values()) {
                billboardConstraints.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.ArmadilloState", billboardConstraints);
        }
        { // CopperWeatherState
            CompoundTag billboardConstraints = new CompoundTag();
            for (var value : net.minecraft.world.level.block.WeatheringCopper.WeatherState.values()) {
                billboardConstraints.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CopperWeatherState", billboardConstraints);
        }
        { // CopperGolemState
            CompoundTag billboardConstraints = new CompoundTag();
            for (var value : CopperGolemState.values()) {
                billboardConstraints.putInt(value.name(), value.ordinal());
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CopperGolemState", billboardConstraints);
        }

        { // PigVariant
            CompoundTag pig = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.PIG_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                pig.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.PigVariant", pig);
        }
        { // CowVariant
            CompoundTag cow = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.COW_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                cow.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CowVariant", cow);
        }
        { // ChickenVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.CHICKEN_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.ChickenVariant", chicken);
        }
        { // WolfSoundVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.WOLF_SOUND_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.WolfSoundVariant", chicken);
        }
        { // ZombieNautilusVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.ZOMBIE_NAUTILUS_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.ZombieNautilusVariant", chicken);
        }
        { // CatSoundVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.CAT_SOUND_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CatSoundVariant", chicken);
        }
        { // ChickenSoundVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.CHICKEN_SOUND_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.ChickenSoundVariant", chicken);
        }
        { // CowSoundVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.COW_SOUND_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CowSoundVariant", chicken);
        }
        { // PigSoundVariant
            CompoundTag chicken = new CompoundTag();
            var idMap = registryAccess.lookupOrThrow(Registries.PIG_SOUND_VARIANT).asHolderIdMap();
            for (var holder : idMap) {
                chicken.putInt(holder.unwrapKey().map(v -> v.identifier().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.PigSoundVariant", chicken);
        }
        { // EntityEvent
            CompoundTag entityEvents = new CompoundTag();
            for (Field field : EntityEvent.class.getDeclaredFields()) {
                field.setAccessible(true);
                entityEvents.putInt(field.getName(), (Byte) field.get(null));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.EntityEvent", entityEvents);
        }

        data.putTag("enums", enums);

        Files.writeString(dataFolder.resolve("mappings.json"), data.toString(NBTToStringStyle.JSON_STYLE_COMPACT));
        MojangNbtReader.writeCompressed(data, dataFolder.resolve("mappings.nbt").toFile());
    }


    private static Map<EntityDataSerializer<?>, String> getEntityDataSerializers() throws Throwable {
        Map<EntityDataSerializer<?>, String> map = new IdentityHashMap<>();
        for (Field field : EntityDataSerializers.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != EntityDataSerializer.class) continue;
            map.put((EntityDataSerializer<?>) field.get(null), field.getName());
        }
        return map;
    }
}
