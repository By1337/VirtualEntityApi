package dev.by1337.virtualentity.core.mappings;

import blib.com.mojang.serialization.Codec;
import blib.com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.network.PacketType;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.syncher.EntityDataSerializer;
import dev.by1337.virtualentity.core.syncher.EntityDataSerializers;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.PluginClassLoader;
import org.by1337.blib.nbt.MojangNbtReader;
import org.by1337.blib.nbt.NbtOps;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Mappings {
    private static final Logger LOGGER = LoggerFactory.getLogger("VirtualEntityApi#Mappings");
    public static final Codec<Mappings> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("serializerToId").forGetter(Mappings::serializerToId),
            Codec.unboundedMap(VirtualEntityType.CODEC, EntityInfo.CODEC).fieldOf("typeToData").forGetter(Mappings::entityTypeToEntityInfo),
            Codec.unboundedMap(Codec.STRING, NetworkValue.CODEC.listOf()).fieldOf("entities").forGetter(Mappings::entityNetworkValues),
            Codec.unboundedMap(Codec.STRING, Codec.unboundedMap(Codec.STRING, Codec.INT)).fieldOf("enums").forGetter(Mappings::enumMappings)
    ).apply(instance, Mappings::new));
    public static final Mappings instance;

    private final Map<String, Integer> serializerToId;
    private final Map<VirtualEntityType, EntityInfo> entityTypeToEntityInfo;
    private final Map<String, List<NetworkValue>> entityNetworkValues;
    private final Map<String, Map<String, Integer>> enumMappings;

    private Mappings(Map<String, Integer> serializerToId, Map<VirtualEntityType, EntityInfo> entityTypeToEntityInfo, Map<String, List<NetworkValue>> entityNetworkValues, Map<String, Map<String, Integer>> enumMappings) {
        this.serializerToId = serializerToId;
        this.entityTypeToEntityInfo = entityTypeToEntityInfo;
        this.entityNetworkValues = entityNetworkValues;
        this.enumMappings = enumMappings;
    }

    public static void load() {
        // ping static block
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

    public Map<String, Map<String, Integer>> enumMappings() {
        return enumMappings;
    }

    private void applyEnumMappings() {
        enumMappings.forEach(this::applyEnumMappings);
    }

    @SuppressWarnings("unchecked")
    private <T extends Enum<T>> void applyEnumMappings(String clazz, Map<String, Integer> mappings) {
        try {
            Class<?> clazz1 = Class.forName(clazz);
            if (!clazz1.isEnum()) throw new IllegalArgumentException("Type " + clazz + " is not enum!");
            Class<T> enumType = (Class<T>) clazz1;

            Field field = enumType.getDeclaredField("TO_ID");
            field.setAccessible(true);

            Map<T, Integer> toId = (Map<T, Integer>) field.get(null);

            for (String s : mappings.keySet()) {
                T val = Enum.valueOf(enumType, s);
                toId.put(val, mappings.get(s));
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to apply mappings for {}", clazz, t);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> EntityDataAccessor<T> findAccessor(String clazz, String id) {
        List<NetworkValue> list = instance.entityNetworkValues.get(clazz);
        if (list == null) {
            return null;
            //   throw new IllegalStateException("Has no mappings for entity " + clazz);
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

    static {
        final InputStream in;
        File file = new File("src/test/resources/mappings.nbt");
        if (file.exists()) {
            LOGGER.info("Using test file mappings");
            try {
                in = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            Plugin plugin = ((PluginClassLoader) Mappings.class.getClassLoader()).getPlugin();
            in = plugin.getResource("entity/" + Version.VERSION + "/mappings.nbt");
            if (in == null) {
                throw new RuntimeException("Could not find mappings file for version " + Version.VERSION);
            }
        }

        try (in){
            CompoundTag nbt = MojangNbtReader.readCompressed(in);
            instance = CODEC.decode(NbtOps.INSTANCE, nbt).getOrThrow().getFirst();
            instance.applyEnumMappings();
        } catch (IOException e) {
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
}
