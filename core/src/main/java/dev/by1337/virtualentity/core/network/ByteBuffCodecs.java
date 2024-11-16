package dev.by1337.virtualentity.core.network;

import dev.by1337.virtualentity.api.entity.Pose;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.nms.NmsUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3f;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.nbt.MojangNbtReader;
import org.by1337.blib.nbt.NBT;
import org.by1337.blib.util.Direction;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.BiConsumer;

public class ByteBuffCodecs {
    public static BiConsumer<Integer, ByteBuf> VAR_INT = ByteBuffUtil::writeVarInt;

    public static BiConsumer<String, ByteBuf> STRING = (s, b) -> {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        VAR_INT.accept(bytes.length, b);
        b.writeBytes(bytes);
    };
    public static BiConsumer<Component, ByteBuf> COMPONENT = (c, b) -> {
        byte[] bytes = GsonComponentSerializer.gson().serializer().toJson(c).getBytes(StandardCharsets.UTF_8);
        VAR_INT.accept(bytes.length, b);
        b.writeBytes(bytes);
    };

    public static <T> void writeOptional(ByteBuf buf, @Nullable T value, BiConsumer<T, ByteBuf> codec) {
        if (value == null) {
            buf.writeBoolean(false);
        } else {
            buf.writeBoolean(true);
            codec.accept(value, buf);
        }
    }

    public static BiConsumer<BlockData, ByteBuf> BLOCK_STATE = (block, b) -> {
        VAR_INT.accept(NmsUtil.getCombinedId(block), b); // todo
    };

    public static BiConsumer<ParticleOptions<?>, ByteBuf> PARTICLE = NmsUtil::writeParticleOptions;

    public static BiConsumer<Vec3f, ByteBuf> VEC3F = (vec, b) -> {
        b.writeFloat(vec.x);
        b.writeFloat(vec.y);
        b.writeFloat(vec.z);
    };
    public static BiConsumer<Vec3i, ByteBuf> BLOCK_POS = (vec, b) -> {
        // https://wiki.vg/Data_types#Position
        b.writeLong(((vec.x & 0x3FFFFFFL) << 38) | ((vec.z & 0x3FFFFFFL) << 12) | (vec.y & 0xFFFL));
    };

    public static BiConsumer<Direction, ByteBuf> DIRECTION = (dir, b) -> {
        VAR_INT.accept(dir.ordinal(), b);
    };

    public static BiConsumer<UUID, ByteBuf> UUID_CODEC = (uuid, b) -> {
        b.writeLong(uuid.getMostSignificantBits());
        b.writeLong(uuid.getLeastSignificantBits());
    };

    public static BiConsumer<@Nullable NBT, ByteBuf> COMPOUND_TAG = (tag, b) -> {
        if (tag == null) {
            b.writeByte(0);
        } else {
            try {
                MojangNbtReader.write(tag, new ByteBufOutputStream(b));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public static BiConsumer<ItemStack, ByteBuf> ITEM_STACK = NmsUtil::writeItemStack;

    public static BiConsumer<Pose, ByteBuf> POSE = (pose, b) -> {
        VAR_INT.accept(pose.ordinal(), b);
    };

}
