package dev.by1337.virtualentity.dumper;

import com.mojang.authlib.GameProfile;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.ProtocolInfo;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.IdDispatchCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.armadillo.Armadillo;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.by1337.blib.nbt.MojangNbtReader;
import org.by1337.blib.nbt.NBT;
import org.by1337.blib.nbt.NBTToStringStyle;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.nbt.impl.ListNBT;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
            Class<?> entityClazz = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
            if (entityClazz == Player.class) {
                entityClazz = ServerPlayer.class;
            }

            Entity entity = (Entity) unsafe.allocateInstance(entityClazz);
            Class<?> c = entity.getClass();
            do { // init base fields
                for (Field f : c.getDeclaredFields()) {
                    f.setAccessible(true);
                    if (Modifier.isStatic(f.getModifiers())) continue;
                    if (f.getType() == Vec3.class) {
                        f.set(entity, new Vec3(0, 0, 0));
                    } else if (f.getType() == BlockPos.class) {
                        f.set(entity, new BlockPos(0, 0, 0));
                    } else if (f.getType() == Direction.class) {
                        f.set(entity, Direction.SOUTH);
                    } else if (f.getType() == GameProfile.class) {
                        f.set(entity, new GameProfile(UUID.randomUUID(), "name"));
                    } else if (f.getType() == SynchedEntityData.class) {
                        Constructor<SynchedEntityData> constructor = SynchedEntityData.class.getDeclaredConstructor(SyncedDataHolder.class, SynchedEntityData.DataItem[].class);
                        constructor.setAccessible(true);
                        f.set(entity, constructor.newInstance(entity, new SynchedEntityData.DataItem[]{}));
                    }
                }
                c = c.getSuperclass();
            } while (c != Object.class);
            if (entity.getClass() == Warden.class) {
                info.putString("spawnPacket", "ADD_ENTITY_PACKET");
            } else {
                ServerEntity serverEntity = new ServerEntity(null, entity, 0, false, p -> {
                }, Set.of());
                Class<?> spawnPacket = entity.getAddEntityPacket(serverEntity).getClass();
                if (spawnPacket == ClientboundAddEntityPacket.class) {
                    info.putString("spawnPacket", "ADD_ENTITY_PACKET");
                } /*else if (spawnPacket == ClientboundAddMobPacket.class) { // removed in 1.19.4
                info.putString("spawnPacket", "ADD_MOB_PACKET");
            }*/ /*else if (spawnPacket == ClientboundAddPlayerPacket.class) { // removed in 1.20.4
                    info.putString("spawnPacket", "ADD_PLAYER_PACKET");
                }*/ else if (spawnPacket == ClientboundAddExperienceOrbPacket.class) {
                    info.putString("spawnPacket", "ADD_EXPERIENCE_ORB_PACKET");
                }/* else if (spawnPacket == ClientboundAddPaintingPacket.class) { // removed in 1.19.4
                info.putString("spawnPacket", "ADD_PAINTING_PACKET");
            }*/ else {
                    throw new IllegalStateException("Unknown spawn packet " + spawnPacket.getCanonicalName());
                }
            }

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
            for (Field field : VillagerProfession.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType() != VillagerProfession.class) continue;
                VillagerProfession villagerProfession = (VillagerProfession) field.get(null);
                profession.putInt(field.getName(), BuiltInRegistries.VILLAGER_PROFESSION.getId(villagerProfession));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.npc.VillagerProfession", profession);
        }
        { // VillagerType
            CompoundTag villagerType = new CompoundTag();
            for (Field field : VillagerType.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getType() != VillagerType.class) continue;
                VillagerType villagerProfession = (VillagerType) field.get(null);
                villagerType.putInt(field.getName(), BuiltInRegistries.VILLAGER_TYPE.getId(villagerProfession));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.npc.VillagerType", villagerType);
        }

        { // PacketType


            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            ProtocolInfo<?> protocolInfo = GameProtocols.CLIENTBOUND_TEMPLATE.bind(RegistryFriendlyByteBuf.decorator(server.registryAccess()));
            IdDispatchCodec idDispatchCodec = (IdDispatchCodec) protocolInfo.codec();
            Field field = IdDispatchCodec.class.getDeclaredField("toId");
            field.setAccessible(true);
            Object2IntMap map = (Object2IntMap) field.get(idDispatchCodec);

            CompoundTag packets = new CompoundTag();
            // PLAYER_INFO_PACKET removed in 1.19.4
            packets.putInt("REMOVE_PLAYER_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundPlayerInfoRemovePacket.class)).type())); // new 1.19.4
            packets.putInt("UPDATE_PLAYER_INFO_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundPlayerInfoUpdatePacket.class)).type()));
            packets.putInt("SET_PLAYER_TEAM_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundSetPlayerTeamPacket.class)).type()));
            packets.putInt("SET_ENTITY_DATA_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundSetEntityDataPacket.class)).type()));
            packets.putInt("ANIMATE_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundAnimatePacket.class)).type()));
            packets.putInt("ROTATE_HEAD_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundRotateHeadPacket.class)).type()));
            packets.putInt("TELEPORT_ENTITY_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundTeleportEntityPacket.class)).type()));
            packets.putInt("ADD_ENTITY_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundAddEntityPacket.class)).type()));
            //packets.putInt("ADD_MOB_PACKET", map.getInt(unsafe .allocateInstance(ClientboundAddMobPacket.class))); // removed in 1.19.4
            //packets.putInt("ADD_PLAYER_PACKET", map.getInt(unsafe .allocateInstance(ClientboundAddPlayerPacket.class))); // removed in 1.20.4
            packets.putInt("ADD_EXPERIENCE_ORB_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundAddExperienceOrbPacket.class)).type()));
            //packets.putInt("ADD_PAINTING_PACKET", map.getInt(unsafe .allocateInstance(ClientboundAddPaintingPacket.class))); // removed in 1.19.4
            packets.putInt("REMOVE_ENTITIES_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundRemoveEntitiesPacket.class)).type()));
            packets.putInt("SET_EQUIPMENT_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundSetEquipmentPacket.class)).type()));
            packets.putInt("MOVE_ENTITY_PACKET_POS", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundMoveEntityPacket.Pos.class)).type()));
            packets.putInt("MOVE_ENTITY_PACKET_POS_ROT", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundMoveEntityPacket.PosRot.class)).type()));
            packets.putInt("MOVE_ENTITY_PACKET_ROT", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundMoveEntityPacket.Rot.class)).type()));
            packets.putInt("SET_ENTITY_MOTION_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundSetEntityMotionPacket.class)).type()));
            packets.putInt("ENTITY_EVENT_PACKET", map.getInt(((Packet<?>) unsafe.allocateInstance(ClientboundEntityEventPacket.class)).type()));
            enums.putTag("dev.by1337.virtualentity.core.network.PacketType", packets);
        }
        { // PaintingMotive
            var idMap = MinecraftServer.getServer().registryAccess().lookupOrThrow(Registries.PAINTING_VARIANT).asHolderIdMap();
            CompoundTag paiting = new CompoundTag();
            for (Holder<PaintingVariant> holder : idMap) {
                paiting.putInt(holder.unwrapKey().map(v -> v.location().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.PaintingMotive", paiting);
        }
        { // CatVariant
            CompoundTag cat = new CompoundTag();
            for (var catVariant : BuiltInRegistries.CAT_VARIANT) {
                var val = BuiltInRegistries.CAT_VARIANT.getKey(catVariant);
                cat.putInt(val.getPath().toUpperCase(Locale.ENGLISH), BuiltInRegistries.CAT_VARIANT.getId(catVariant));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.CatVariant", cat);
        }
        { // FrogVariant
            CompoundTag frog = new CompoundTag();
            for (var catVariant : BuiltInRegistries.FROG_VARIANT) {
                var val = BuiltInRegistries.FROG_VARIANT.getKey(catVariant);
                frog.putInt(val.getPath().toUpperCase(Locale.ENGLISH), BuiltInRegistries.FROG_VARIANT.getId(catVariant));
            }
            enums.putTag("dev.by1337.virtualentity.api.entity.FrogVariant", frog);
        }

        { // WolfVariant
            var idMap = MinecraftServer.getServer().registryAccess().lookupOrThrow(Registries.WOLF_VARIANT).asHolderIdMap();
            CompoundTag wolf = new CompoundTag();
            for (Holder<WolfVariant> holder : idMap) {
                wolf.putInt(holder.unwrapKey().map(v -> v.location().getPath()).get().toUpperCase(Locale.ENGLISH), idMap.getIdOrThrow(holder));
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
