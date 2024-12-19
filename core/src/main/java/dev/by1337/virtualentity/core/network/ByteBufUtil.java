package dev.by1337.virtualentity.core.network;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.MappedEnum;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.nms.NmsUtil;
import io.netty.buffer.ByteBuf;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3f;
import org.by1337.blib.geom.Vec3i;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class ByteBufUtil {
    private static final int SEGMENT_BITS = 0x7F;
    private static final int CONTINUE_BIT = 0x80;
    private static final boolean IS_1_20_4_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_20_4);

    public static int readVarInt(ByteBuf byteBuf) {

        int value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = byteBuf.readByte();
            value |= (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 32) throw new RuntimeException("VarInt is too big");
        }

        return value;
    }

    public static long readVarLong(ByteBuf byteBuf) {
        long value = 0;
        int position = 0;
        byte currentByte;

        while (true) {
            currentByte = byteBuf.readByte();
            value |= (long) (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) break;

            position += 7;

            if (position >= 64) throw new RuntimeException("VarLong is too big");
        }

        return value;
    }

    public static void writeVarInt(int value, ByteBuf byteBuf) {
        while (true) {
            if ((value & ~SEGMENT_BITS) == 0) {
                byteBuf.writeByte(value);
                return;
            }

            byteBuf.writeByte((value & SEGMENT_BITS) | CONTINUE_BIT);
            value >>>= 7;
        }
    }

    public static void writeVarLong(long value, ByteBuf byteBuf) {
        while (true) {
            if ((value & ~((long) SEGMENT_BITS)) == 0) {
                byteBuf.writeByte((int) value);
                return;
            }
            byteBuf.writeByte((int) ((value & SEGMENT_BITS) | CONTINUE_BIT));
            value >>>= 7;
        }
    }

    public static UUID readUUID(ByteBuf byteBuf) {
        return new UUID(byteBuf.readLong(), byteBuf.readLong());
    }

    public static void writeUUID(UUID uuid, ByteBuf byteBuf) {
        byteBuf.writeLong(uuid.getMostSignificantBits());
        byteBuf.writeLong(uuid.getLeastSignificantBits());
    }

    public static void writeUtf(String s, ByteBuf byteBuf) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length, byteBuf);
        byteBuf.writeBytes(bytes);
    }


    public static void writeComponent(Component c, ByteBuf byteBuf) {
        if (IS_1_20_4_OR_NEWER) {
            NmsUtil.writeComponent(c, byteBuf);
        } else {
            byte[] bytes = GsonComponentSerializer.gson().serializer().toJson(c).getBytes(StandardCharsets.UTF_8);
            writeVarInt(bytes.length, byteBuf);
            byteBuf.writeBytes(bytes);
        }
    }

    public static <T> void writeOptional(ByteBuf buf, @Nullable T value, BiConsumer<T, ByteBuf> codec) {
        if (value == null) {
            buf.writeBoolean(false);
        } else {
            buf.writeBoolean(true);
            codec.accept(value, buf);
        }
    }

    public static void writeBlockState(BlockData blockData, ByteBuf byteBuf) {
        writeVarInt(NmsUtil.getCombinedId(blockData), byteBuf);
    }

    public static void writeParticle(ParticleOptions<?> particleOptions, ByteBuf byteBuf) {
        NmsUtil.writeParticleOptions(particleOptions, byteBuf);
    }

    @SinceMinecraftVersion("1.20.6")
    public static void writeParticles(List<ParticleOptions<?>> particleOptions, ByteBuf byteBuf) {
        NmsUtil.writeParticles(particleOptions, byteBuf);
    }

    public static void writeVec3f(Vec3f vec3f, ByteBuf byteBuf) {
        byteBuf.writeFloat(vec3f.x);
        byteBuf.writeFloat(vec3f.y);
        byteBuf.writeFloat(vec3f.z);
    }

    public static void writeBlockPos(Vec3i pos, ByteBuf byteBuf) {
        byteBuf.writeLong(((pos.x & 0x3FFFFFFL) << 38) | ((pos.z & 0x3FFFFFFL) << 12) | (pos.y & 0xFFFL));
    }

    public static <T extends Enum<T>> void writeEnum(T val, ByteBuf byteBuf) {
        if (val instanceof MappedEnum mappedEnum) {
            writeVarInt(mappedEnum.getId(), byteBuf);
        } else {
            writeVarInt(val.ordinal(), byteBuf);
        }
    }

    public static void writeNbt(@Nullable CompoundTag nbt, ByteBuf byteBuf) {
        NmsUtil.writeCompoundTag(nbt, byteBuf);
    }

    public static void writeItemStack(ItemStack itemStack, ByteBuf byteBuf) {
        NmsUtil.writeItemStack(itemStack, byteBuf);
    }
}
