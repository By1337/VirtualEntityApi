package dev.by1337.virtualentity.core.syncher;

import dev.by1337.virtualentity.api.entity.Pose;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffCodecs;
import net.kyori.adventure.text.Component;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3f;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;

import java.util.*;

public class EntityDataSerializers {
    private static final Map<String, EntityDataSerializer<?>> SERIALIZERS = new HashMap<>();

    public static final EntityDataSerializer<Byte> BYTE = register((val, byteBuf) -> {
        byteBuf.writeByte(val);
    }, "BYTE");

    public static final EntityDataSerializer<Integer> INT = register((val, byteBuf) -> {
        ByteBuffCodecs.VAR_INT.accept(val, byteBuf);
    }, "INT");

    public static final EntityDataSerializer<Float> FLOAT = register((val, byteBuf) -> {
        byteBuf.writeFloat(val);
    }, "FLOAT");

    public static final EntityDataSerializer<String> STRING = register((val, byteBuf) -> {
        ByteBuffCodecs.STRING.accept(val, byteBuf);
    }, "STRING");

    public static final EntityDataSerializer<Component> COMPONENT = register((val, byteBuf) -> {
        ByteBuffCodecs.COMPONENT.accept(val, byteBuf);
    }, "COMPONENT");

    public static final EntityDataSerializer<Optional<Component>> OPTIONAL_COMPONENT = register((val, byteBuf) -> {
        ByteBuffCodecs.writeOptional(byteBuf, val.orElse(null), ByteBuffCodecs.COMPONENT);
    }, "OPTIONAL_COMPONENT");

    public static final EntityDataSerializer<ItemStack> ITEM_STACK = register((val, byteBuf) -> {
        ByteBuffCodecs.ITEM_STACK.accept(val, byteBuf);
    }, "ITEM_STACK");

    public static final EntityDataSerializer<Optional<BlockData>> BLOCK_STATE = register((val, byteBuf) -> {
        ByteBuffCodecs.writeOptional(byteBuf, val.orElse(null), ByteBuffCodecs.BLOCK_STATE);
    }, "BLOCK_STATE");

    public static final EntityDataSerializer<Boolean> BOOLEAN = register((val, byteBuf) -> {
        byteBuf.writeBoolean(val);
    }, "BOOLEAN");

    public static final EntityDataSerializer<ParticleOptions<?>> PARTICLE = register((val, byteBuf) -> {
        ByteBuffCodecs.PARTICLE.accept(val, byteBuf);
    }, "PARTICLE");

    public static final EntityDataSerializer<Vec3f> ROTATIONS = register((val, byteBuf) -> {
        ByteBuffCodecs.VEC3F.accept(val, byteBuf);
    }, "ROTATIONS");

    public static final EntityDataSerializer<Vec3i> BLOCK_POS = register((val, byteBuf) -> {
        ByteBuffCodecs.BLOCK_POS.accept(val, byteBuf);
    }, "BLOCK_POS");

    public static final EntityDataSerializer<Optional<Vec3i>> OPTIONAL_BLOCK_POS = register((val, byteBuf) -> {
        ByteBuffCodecs.writeOptional(byteBuf, val.orElse(null), ByteBuffCodecs.BLOCK_POS);
    }, "OPTIONAL_BLOCK_POS");

    public static final EntityDataSerializer<Direction> DIRECTION = register((val, byteBuf) -> {
        ByteBuffCodecs.DIRECTION.accept(val, byteBuf);
    }, "DIRECTION");

    public static final EntityDataSerializer<Optional<UUID>> OPTIONAL_UUID = register((val, byteBuf) -> {
        ByteBuffCodecs.writeOptional(byteBuf, val.orElse(null), ByteBuffCodecs.UUID_CODEC);
    }, "OPTIONAL_UUID");

    public static final EntityDataSerializer<CompoundTag> COMPOUND_TAG = register((val, byteBuf) -> {
        ByteBuffCodecs.COMPOUND_TAG.accept(val, byteBuf);
    }, "COMPOUND_TAG");

    public static final EntityDataSerializer<VillagerData> VILLAGER_DATA = register((val, byteBuf) -> {
        Mappings mappings = Mappings.instance;
        Integer type = mappings.villagerTypeMappings().typeToId().get(val.type());
        if (type == null) {
            throw new IllegalStateException("Нет мапингов для villager type " + val.type() + " на версии " + Version.VERSION);
        }
        Integer profession = mappings.villagerTypeMappings().professionToId().get(val.profession());
        if (profession == null) {
            throw new IllegalStateException("Нет мапингов для villager profession " + val.profession() + " на версии " + Version.VERSION);
        }
        INT.write(type, byteBuf);
        INT.write(profession, byteBuf);
        INT.write(val.lvl(), byteBuf);
    }, "VILLAGER_DATA");

    public static final EntityDataSerializer<OptionalInt> OPTIONAL_UNSIGNED_INT = register((val, byteBuf) -> {
        INT.write(val.orElse(-1) + 1, byteBuf);
    }, "OPTIONAL_UNSIGNED_INT");

    public static final EntityDataSerializer<Pose> POSE = register((val, byteBuf) -> {
        ByteBuffCodecs.POSE.accept(val, byteBuf);
    }, "POSE");

    private static <T> EntityDataSerializer<T> register(EntityDataSerializer<T> serializer, String name) {
        if (SERIALIZERS.put(name, serializer) != null) {
            throw new IllegalArgumentException("Duplicate serializer: " + name);
        }
        return serializer;
    }

    public static EntityDataSerializer<?> getByName(String name) {
        return SERIALIZERS.get(name);
    }
}
