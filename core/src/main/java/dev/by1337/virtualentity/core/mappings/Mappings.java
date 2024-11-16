package dev.by1337.virtualentity.core.mappings;

import blib.com.mojang.serialization.Codec;
import blib.com.mojang.serialization.JsonOps;
import blib.com.mojang.serialization.codecs.RecordCodecBuilder;
import com.google.gson.JsonParser;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.npc.VillagerProfession;
import dev.by1337.virtualentity.api.entity.npc.VillagerType;
import dev.by1337.virtualentity.core.network.PacketType;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.syncher.EntityDataSerializer;
import dev.by1337.virtualentity.core.syncher.EntityDataSerializers;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.PluginClassLoader;
import org.by1337.blib.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class Mappings {
    private static final Logger LOGGER = LoggerFactory.getLogger("VirtualEntityApi#Mappings");
    public static final Codec<Mappings> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("serializerToId").forGetter(Mappings::serializerToId),
            Codec.unboundedMap(VirtualEntityType.CODEC, EntityInfo.CODEC).fieldOf("typeToData").forGetter(Mappings::entityTypeToEntityInfo),
            Codec.unboundedMap(Codec.STRING, NetworkValue.CODEC.listOf()).fieldOf("entities").forGetter(Mappings::entityNetworkValues),
            Codec.unboundedMap(PacketType.CODEC, Codec.INT).fieldOf("packets").forGetter(Mappings::packetToId),
            VillagerTypeMappings.CODEC.fieldOf("villagerData").forGetter(Mappings::villagerTypeMappings)
    ).apply(instance, Mappings::new));
    public static final Mappings instance;


    private final Map<String, Integer> serializerToId;
    private final Map<VirtualEntityType, EntityInfo> entityTypeToEntityInfo;
    private final Map<String, List<NetworkValue>> entityNetworkValues;
    private final Map<PacketType, Integer> packetToId;
    private final VillagerTypeMappings villagerTypeMappings;

    public Mappings(Map<String, Integer> serializerToId, Map<VirtualEntityType, EntityInfo> entityTypeToEntityInfo, Map<String, List<NetworkValue>> entityNetworkValues, Map<PacketType, Integer> packetToId, VillagerTypeMappings villagerTypeMappings) {
        this.serializerToId = serializerToId;
        this.entityTypeToEntityInfo = entityTypeToEntityInfo;
        this.entityNetworkValues = entityNetworkValues;
        this.packetToId = packetToId;
        this.villagerTypeMappings = villagerTypeMappings;
    }

    public Map<String, Integer> serializerToId() {
        return serializerToId;
    }

    public Map<VirtualEntityType, EntityInfo> entityTypeToEntityInfo() {
        return entityTypeToEntityInfo;
    }

    public Map<String, List<NetworkValue>> entityNetworkValues() {
        return entityNetworkValues;
    }

    public Map<PacketType, Integer> packetToId() {
        return packetToId;
    }

    public VillagerTypeMappings villagerTypeMappings() {
        return villagerTypeMappings;
    }

    @SuppressWarnings("unchecked")
    public static <T> EntityDataAccessor<T> findAccessor(String clazz, String id) {
        List<NetworkValue> list = instance.entityNetworkValues.get(clazz);
        if (list == null) {
            throw new IllegalStateException("Has no mappings for entity " + clazz);
        }
        NetworkValue value = list.stream().filter(v -> v.name.equals(id)).findFirst().orElse(null);
        if (value == null) {
            throw new IllegalStateException("Has no NetworkValue " + id + " for entity " + clazz);
        }
        EntityDataSerializer<?> serializer = EntityDataSerializers.getByName(value.type);
        if (serializer == null) {
            throw new IllegalStateException("Unknown EntityDataSerializer type " + value.type);
        }
        return (EntityDataAccessor<T>) new EntityDataAccessor<>(value.id, serializer);
    }

    public static int getNetworkId(VirtualEntityType type) {
        EntityInfo entityInfo = instance.entityTypeToEntityInfo.get(type);
        if (entityInfo == null) {
            throw new IllegalStateException("Has no EntityInfo for type " + type + " Version: " + Version.VERSION);
        }
        return entityInfo.networkId;
    }

    public static PacketType getSpawnPacket(VirtualEntityType type) {
        EntityInfo entityInfo = instance.entityTypeToEntityInfo.get(type);
        if (entityInfo == null) {
            throw new IllegalStateException("Has no EntityInfo for type " + type + " Version: " + Version.VERSION);
        }
        return entityInfo.spawnPacket;
    }

    public static int getPacketId(PacketType packetType) {
        Integer integer = instance.packetToId.get(packetType);
        if (integer == null) {
            throw new IllegalStateException("Has no packet id for packet " + packetType + " Version: " + Version.VERSION);
        }
        return integer;
    }

    static {
       final InputStream in;
        File file = new File("src/test/resources/mappings.json");
        if (file.exists()) {
            LOGGER.info("Using test file mappings");
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            Plugin plugin = ((PluginClassLoader) Mappings.class.getClassLoader()).getPlugin();
            in = plugin.getResource("entity/" + Version.VERSION + "/mappings.json");
            if (in == null) {
                throw new RuntimeException("Could not find mappings file for version " + Version.VERSION);
            }
        }


        try (var reader = new InputStreamReader(in)) {
            JsonParser jsonParser = new JsonParser();
            instance = CODEC.decode(JsonOps.INSTANCE, jsonParser.parse(reader)).getOrThrow().getFirst();
        } catch (Throwable e) {
            throw new RuntimeException("Failed to read mappings file for version " + Version.VERSION, e);
        }
    }

    public record EntityInfo(int networkId, PacketType spawnPacket) {
        public static final Codec<EntityInfo> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("networkId").forGetter(EntityInfo::networkId),
                PacketType.CODEC.fieldOf("spawnPacket").forGetter(EntityInfo::spawnPacket)
        ).apply(instance, EntityInfo::new));
    }


    public record NetworkValue(String name, int id, String type) {
        public static final Codec<NetworkValue> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter(NetworkValue::name),
                Codec.INT.fieldOf("id").forGetter(NetworkValue::id),
                Codec.STRING.fieldOf("type").forGetter(NetworkValue::type)
        ).apply(instance, NetworkValue::new));
    }

    public record VillagerTypeMappings(Map<VillagerProfession, Integer> professionToId,
                                       Map<VillagerType, Integer> typeToId) {
        public static final Codec<VillagerTypeMappings> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.unboundedMap(VillagerProfession.CODEC, Codec.INT).fieldOf("profession").forGetter(VillagerTypeMappings::professionToId),
                Codec.unboundedMap(VillagerType.CODEC, Codec.INT).fieldOf("type").forGetter(VillagerTypeMappings::typeToId)
        ).apply(instance, VillagerTypeMappings::new));
    }
}
