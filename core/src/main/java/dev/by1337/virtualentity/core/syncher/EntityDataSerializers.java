package dev.by1337.virtualentity.core.syncher;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.*;
import dev.by1337.virtualentity.api.entity.npc.VillagerData;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
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

    public static final EntityDataSerializer<Integer> INT = register(ByteBufUtil::writeVarInt, "INT");

    public static final EntityDataSerializer<Float> FLOAT = register((val, byteBuf) -> byteBuf.writeFloat(val), "FLOAT");

    public static final EntityDataSerializer<String> STRING = register(ByteBufUtil::writeUtf, "STRING");

    public static final EntityDataSerializer<Component> COMPONENT = register(ByteBufUtil::writeComponent, "COMPONENT");

    public static final EntityDataSerializer<Optional<Component>> OPTIONAL_COMPONENT = register((val, byteBuf) -> {
        ByteBufUtil.writeOptional(byteBuf, val.orElse(null), ByteBufUtil::writeComponent);
    }, "OPTIONAL_COMPONENT");

    public static final EntityDataSerializer<ItemStack> ITEM_STACK = register(ByteBufUtil::writeItemStack, "ITEM_STACK");

    @SuppressWarnings("rawtypes")
    public static final EntityDataSerializer BLOCK_STATE; // 1.19.4< is Optional<BlockData> 1.19.4>= is BlockData

    public static final EntityDataSerializer<Boolean> BOOLEAN = register((val, byteBuf) -> {
        byteBuf.writeBoolean(val);
    }, "BOOLEAN");

    public static final EntityDataSerializer<ParticleOptions<?>> PARTICLE = register(ByteBufUtil::writeParticle, "PARTICLE");

    @SinceMinecraftVersion("1.20.6")
    public static final EntityDataSerializer<List<ParticleOptions<?>>> PARTICLES = register(ByteBufUtil::writeParticles, "PARTICLES");

    public static final EntityDataSerializer<Vec3f> ROTATIONS = register(ByteBufUtil::writeVec3f, "ROTATIONS");

    public static final EntityDataSerializer<Vec3i> BLOCK_POS = register(ByteBufUtil::writeBlockPos, "BLOCK_POS");

    public static final EntityDataSerializer<Optional<Vec3i>> OPTIONAL_BLOCK_POS = register((val, byteBuf) -> {
        ByteBufUtil.writeOptional(byteBuf, val.orElse(null), ByteBufUtil::writeBlockPos);
    }, "OPTIONAL_BLOCK_POS");

    public static final EntityDataSerializer<Direction> DIRECTION = register(ByteBufUtil::writeEnum, "DIRECTION");

    public static final EntityDataSerializer<Optional<UUID>> OPTIONAL_UUID = register((val, byteBuf) -> {
        ByteBufUtil.writeOptional(byteBuf, val.orElse(null), ByteBufUtil::writeUUID);
    }, "OPTIONAL_UUID");

    public static final EntityDataSerializer<CompoundTag> COMPOUND_TAG = register(ByteBufUtil::writeNbt, "COMPOUND_TAG");

    public static final EntityDataSerializer<VillagerData> VILLAGER_DATA = register((val, byteBuf) -> {
        INT.write(val.type().getId(), byteBuf);
        INT.write(val.profession().getId(), byteBuf);
        INT.write(val.lvl(), byteBuf);
    }, "VILLAGER_DATA");

    public static final EntityDataSerializer<OptionalInt> OPTIONAL_UNSIGNED_INT = register((val, byteBuf) -> {
        INT.write(val.orElse(-1) + 1, byteBuf);
    }, "OPTIONAL_UNSIGNED_INT");

    public static final EntityDataSerializer<Pose> POSE = register(ByteBufUtil::writeEnum, "POSE");

    public static final EntityDataSerializer<Quaternionf> QUATERNION = register((val, byteBuf) -> {
        byteBuf.writeFloat(val.x);
        byteBuf.writeFloat(val.y);
        byteBuf.writeFloat(val.z);
        byteBuf.writeFloat(val.w);
    }, "QUATERNION");

    public static final EntityDataSerializer<SnifferState> SNIFFER_STATE = register(ByteBufUtil::writeEnum, "SNIFFER_STATE");
    public static final EntityDataSerializer<CatVariant> CAT_VARIANT = register(ByteBufUtil::writeEnum, "CAT_VARIANT");
    public static final EntityDataSerializer<FrogVariant> FROG_VARIANT = register(ByteBufUtil::writeEnum, "FROG_VARIANT");
    public static final EntityDataSerializer<PaintingMotive> PAINTING_VARIANT = register(ByteBufUtil::writeEnum, "PAINTING_VARIANT");
    public static final EntityDataSerializer<WolfVariant> WOLF_VARIANT = register(ByteBufUtil::writeEnum, "WOLF_VARIANT");
    public static final EntityDataSerializer<ArmadilloState> ARMADILLO_STATE = register(ByteBufUtil::writeEnum, "ARMADILLO_STATE");
    public static final EntityDataSerializer<Long> LONG = register(ByteBufUtil::writeVarLong, "LONG");
    public static final EntityDataSerializer<Optional<BlockData>> OPTIONAL_BLOCK_STATE = register((val, buff) -> {
        if (val.isPresent()) {
            ByteBufUtil.writeBlockState(val.get(), buff);
        } else {
            ByteBufUtil.writeVarInt(0, buff);
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
                ByteBufUtil.writeBlockState(blockData, byteBuf);
            }, "BLOCK_STATE");
        } else {
            BLOCK_STATE = register((val, byteBuf) -> {
                @SuppressWarnings("unchecked")
                Optional<BlockData> opt = (Optional<BlockData>) val;
                ByteBufUtil.writeOptional(byteBuf, opt.orElse(null), ByteBufUtil::writeBlockState);
            }, "BLOCK_STATE");
        }
    }
}
