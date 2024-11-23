package dev.by1337.virtualentity.core.syncher;

import dev.by1337.virtualentity.api.entity.*;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBuffUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3f;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.*;

public class EntityDataSerializers {
    private static final Map<String, EntityDataSerializer<?>> SERIALIZERS = new HashMap<>();
    private static final Map<EntityDataSerializer<?>, Integer> SERIALIZER_TO_ID = new IdentityHashMap<>();
    private static final Map<EntityDataSerializer<?>, String> SERIALIZER_TO_NAME = new IdentityHashMap<>();

    public static final EntityDataSerializer<Byte> BYTE = register((val, byteBuf) -> {
        byteBuf.writeByte(val);
    }, "BYTE");

    public static final EntityDataSerializer<Integer> INT = register(ByteBuffUtil::writeVarInt, "INT");

    public static final EntityDataSerializer<Float> FLOAT = register((val, byteBuf) -> byteBuf.writeFloat(val), "FLOAT");

    public static final EntityDataSerializer<String> STRING = register(ByteBuffUtil::writeUtf, "STRING");

    public static final EntityDataSerializer<Component> COMPONENT = register(ByteBuffUtil::writeComponent, "COMPONENT");

    public static final EntityDataSerializer<Optional<Component>> OPTIONAL_COMPONENT = register((val, byteBuf) -> {
        ByteBuffUtil.writeOptional(byteBuf, val.orElse(null), ByteBuffUtil::writeComponent);
    }, "OPTIONAL_COMPONENT");

    public static final EntityDataSerializer<ItemStack> ITEM_STACK = register(ByteBuffUtil::writeItemStack, "ITEM_STACK");

    @SuppressWarnings("rawtypes")
    public static final EntityDataSerializer BLOCK_STATE; // 1.19.4< is Optional<BlockData> 1.19.4>= is BlockData

    public static final EntityDataSerializer<Boolean> BOOLEAN = register((val, byteBuf) -> {
        byteBuf.writeBoolean(val);
    }, "BOOLEAN");

    public static final EntityDataSerializer<ParticleOptions<?>> PARTICLE = register(ByteBuffUtil::writeParticle, "PARTICLE");

    public static final EntityDataSerializer<Vec3f> ROTATIONS = register(ByteBuffUtil::writeVec3f, "ROTATIONS");

    public static final EntityDataSerializer<Vec3i> BLOCK_POS = register(ByteBuffUtil::writeBlockPos, "BLOCK_POS");

    public static final EntityDataSerializer<Optional<Vec3i>> OPTIONAL_BLOCK_POS = register((val, byteBuf) -> {
        ByteBuffUtil.writeOptional(byteBuf, val.orElse(null), ByteBuffUtil::writeBlockPos);
    }, "OPTIONAL_BLOCK_POS");

    public static final EntityDataSerializer<Direction> DIRECTION = register(ByteBuffUtil::writeEnum, "DIRECTION");

    public static final EntityDataSerializer<Optional<UUID>> OPTIONAL_UUID = register((val, byteBuf) -> {
        ByteBuffUtil.writeOptional(byteBuf, val.orElse(null), ByteBuffUtil::writeUUID);
    }, "OPTIONAL_UUID");

    public static final EntityDataSerializer<CompoundTag> COMPOUND_TAG = register(ByteBuffUtil::writeNbt, "COMPOUND_TAG");

    public static final EntityDataSerializer<VillagerData> VILLAGER_DATA = register((val, byteBuf) -> {
        INT.write(val.type().getId(), byteBuf);
        INT.write(val.profession().getId(), byteBuf);
        INT.write(val.lvl(), byteBuf);
    }, "VILLAGER_DATA");

    public static final EntityDataSerializer<OptionalInt> OPTIONAL_UNSIGNED_INT = register((val, byteBuf) -> {
        INT.write(val.orElse(-1) + 1, byteBuf);
    }, "OPTIONAL_UNSIGNED_INT");

    public static final EntityDataSerializer<Pose> POSE = register(ByteBuffUtil::writeEnum, "POSE");

    public static final EntityDataSerializer<Quaternionf> QUATERNION = register((val, byteBuf) -> {
        byteBuf.writeFloat(val.x);
        byteBuf.writeFloat(val.y);
        byteBuf.writeFloat(val.z);
        byteBuf.writeFloat(val.w);
    }, "QUATERNION");

    public static final EntityDataSerializer<SnifferState> SNIFFER_STATE = register(ByteBuffUtil::writeEnum, "SNIFFER_STATE");
    public static final EntityDataSerializer<CatVariant> CAT_VARIANT = register(ByteBuffUtil::writeEnum, "CAT_VARIANT");
    public static final EntityDataSerializer<FrogVariant> FROG_VARIANT = register(ByteBuffUtil::writeEnum, "FROG_VARIANT");
    public static final EntityDataSerializer<PaintingMotive> PAINTING_VARIANT = register(ByteBuffUtil::writeEnum, "PAINTING_VARIANT");
    public static final EntityDataSerializer<Long> LONG = register(ByteBuffUtil::writeVarLong, "LONG");
    public static final EntityDataSerializer<Optional<BlockData>> OPTIONAL_BLOCK_STATE = register((val, buff) -> {
        if (val.isPresent()) {
            ByteBuffUtil.writeBlockState(val.get(), buff);
        } else {
            ByteBuffUtil.writeVarInt(0, buff);
        }
    }, "OPTIONAL_BLOCK_STATE");
    public static final EntityDataSerializer<Vector3f> VECTOR3 = register((val, buff) -> {
        buff.writeFloat(val.x);
        buff.writeFloat(val.y);
        buff.writeFloat(val.z);
    }, "VECTOR3");

    // OPTIONAL_GLOBAL_POS unused

    private static <T> EntityDataSerializer<T> register(EntityDataSerializer<T> serializer, String name) {
        if (SERIALIZERS.put(name, serializer) != null) {
            throw new IllegalArgumentException("Duplicate serializer: " + name);
        }
        Integer id = Mappings.instance.serializerToId().get(name);
        if (id != null) {
            SERIALIZER_TO_ID.put(serializer, id);
        }
        SERIALIZER_TO_NAME.put(serializer, name);
        return serializer;
    }

    public static EntityDataSerializer<?> getByName(String name) {
        return SERIALIZERS.get(name);
    }

    public static int getId(EntityDataSerializer<?> serializer) {
        Integer id = SERIALIZER_TO_ID.get(serializer);
        if (id == null) {
            throw new IllegalStateException("Has no EntityDataSerializer id for serializer " + SERIALIZER_TO_NAME.get(serializer) + " Version: " + Version.VERSION);
        }
        return id;
    }

    static {
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            BLOCK_STATE = register((val, byteBuf) -> {
                BlockData blockData = (BlockData) val;
                ByteBuffUtil.writeBlockState(blockData, byteBuf);
            }, "BLOCK_STATE");
        } else {
            BLOCK_STATE = register((val, byteBuf) -> {
                @SuppressWarnings("unchecked")
                Optional<BlockData> opt = (Optional<BlockData>) val;
                ByteBuffUtil.writeOptional(byteBuf, opt.orElse(null), ByteBuffUtil::writeBlockState);
            }, "BLOCK_STATE");
        }
    }
}
