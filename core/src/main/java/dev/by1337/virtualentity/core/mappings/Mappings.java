package dev.by1337.virtualentity.core.mappings;

import blib.com.mojang.serialization.Codec;
import blib.com.mojang.serialization.JsonOps;
import blib.com.mojang.serialization.codecs.RecordCodecBuilder;
import com.google.gson.JsonParser;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.entity.npc.VillagerProfession;
import dev.by1337.virtualentity.api.entity.npc.VillagerType;
import dev.by1337.virtualentity.core.network.PacketType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.PluginClassLoader;
import org.by1337.blib.util.Version;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Mappings {
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

    static {
        Plugin plugin = ((PluginClassLoader) Mappings.class.getClassLoader()).getPlugin();
        var in = plugin.getResource(Version.VERSION + "/mappings.json");
        if (in == null) {
            throw new RuntimeException("Could not find mappings file for version " + Version.VERSION);
        }
        try (in) {
            String data = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            JsonParser jsonParser = new JsonParser();
            instance = CODEC.decode(JsonOps.INSTANCE, jsonParser.parse(data)).getOrThrow().getFirst();

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
