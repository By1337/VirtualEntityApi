package dev.by1337.virtualentity.core.nms;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.annotations.ASM;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.kyori.adventure.text.Component;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.BLib;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class NmsUtil {
    private static final NmsAccessor accessor;

    public static int getCombinedId(BlockData blockData) {
        return accessor.getBlockId(blockData);
    }

    public static void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
        accessor.writeParticleOptions(particleOptions, b);
    }

    public static void writeItemStack(ItemStack itemStack, ByteBuf b) {
        accessor.writeItemStack(itemStack, b);
    }

    public static Channel getChannel(Player player) {
        return accessor.getChannel(player);
    }

    public static void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
        accessor.writeCompoundTag(compoundTag, b);
    }

    @SinceMinecraftVersion("1.20.4")
    public static void writeComponent(Component component, ByteBuf b) {
        accessor.writeComponent(component, b);
    }

    @SinceMinecraftVersion("1.20.6")
    public static void writeParticles(List<ParticleOptions<?>> list, ByteBuf b) {
        accessor.writeParticles(list, b);
    }

    static {
        accessor = switch (Version.VERSION) {
            case V1_16_5 -> new NmsAccessorV1_16_5();
            case V1_17_1 -> new NmsAccessorV1_17_1();
            case V1_18_2 -> new NmsAccessorV1_18_2();
            case V1_19_4 -> new NmsAccessorV1_19_4();
            case V1_20_1 -> new NmsAccessorV1_20_1();
            case V1_20_4 -> new NmsAccessorV1_20_4();
            default -> new NmsAccessorV1_20_6();
            //default -> throw new UnsupportedOperationException("Unsupported version " + Version.VERSION.getVer());
        };
    }


    private interface NmsAccessor {
        int getBlockId(BlockData blockData);

        default void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
            writeParticle(particleOptions.particle(), particleOptions.value(), b);
        }

        void writeParticle(Particle particle, @Nullable Object value, ByteBuf b);

        void writeItemStack(ItemStack itemStack, ByteBuf b);

        Channel getChannel(Player player);

        void writeCompoundTag(CompoundTag compoundTag, ByteBuf b);

        default void writeComponent(Component component, ByteBuf b) {
            throw new UnsupportedOperationException("Unsupported version " + Version.VERSION);
        }

        default void writeParticles(List<ParticleOptions<?>> list, ByteBuf b) {
            throw new UnsupportedOperationException("Unsupported version " + Version.VERSION);
        }
    }

    private static class NmsAccessorV1_16_5 implements NmsAccessor {

        @Override
        @ASM
        public int getBlockId(BlockData blockData) {
            // source
            // return Block.getCombinedId(((CraftBlockData) blockData).getState());
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_16_R3/block/data/CraftBlockData
                        invokevirtual org/bukkit/craftbukkit/v1_16_R3/block/data/CraftBlockData getState ()Lnet/minecraft/server/v1_16_R3/IBlockData;
                        invokestatic net/minecraft/server/v1_16_R3/Block getCombinedId (Lnet/minecraft/server/v1_16_R3/IBlockData;)I
                        ireturn
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
            // source
            // var nms = CraftParticle.toNMS(particleOptions.particle(), particleOptions.value());
            // ByteBufUtil.writeVarInt(Registry.PARTICLE_TYPE.getId(nms.getParticle()), b);
            // nms.writeToNetwork(new FriendlyByteBuf(b));
            String asm = """
                    A:
                        aload 1
                        invokevirtual dev/by1337/virtualentity/api/particles/ParticleOptions particle ()Lorg/bukkit/Particle;
                        aload 1
                        invokevirtual dev/by1337/virtualentity/api/particles/ParticleOptions value ()Ljava/lang/Object;
                        invokestatic org/bukkit/craftbukkit/v1_16_R3/CraftParticle toNMS (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/server/v1_16_R3/ParticleParam;
                        astore 3
                    B:
                        getstatic net/minecraft/server/v1_16_R3/IRegistry PARTICLE_TYPE Lnet/minecraft/server/v1_16_R3/IRegistry;
                        aload 3
                        invokeinterface net/minecraft/server/v1_16_R3/ParticleParam getParticle ()Lnet/minecraft/server/v1_16_R3/Particle;
                        invokevirtual net/minecraft/server/v1_16_R3/IRegistry a (Ljava/lang/Object;)I
                        aload 2
                        invokestatic dev/by1337/virtualentity/core/network/ByteBufUtil writeVarInt (ILio/netty/buffer/ByteBuf;)V
                    C:
                        aload 3
                        new net/minecraft/server/v1_16_R3/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/server/v1_16_R3/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokeinterface net/minecraft/server/v1_16_R3/ParticleParam a (Lnet/minecraft/server/v1_16_R3/PacketDataSerializer;)V
                    D:
                        return
                    E:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            writeParticleOptions(new ParticleOptions<>(value, particle), b);
        }

        @Override
        @ASM
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeItem(CraftItemStack.asNMSCopy(itemStack));
            String asm = """
                    A:
                        new net/minecraft/server/v1_16_R3/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/server/v1_16_R3/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        aload 1
                        invokestatic org/bukkit/craftbukkit/v1_16_R3/inventory/CraftItemStack asNMSCopy (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/server/v1_16_R3/ItemStack;
                        invokevirtual net/minecraft/server/v1_16_R3/PacketDataSerializer a (Lnet/minecraft/server/v1_16_R3/ItemStack;)Lnet/minecraft/server/v1_16_R3/PacketDataSerializer;
                        pop
                    C:
                        return
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public Channel getChannel(Player player) {
            // source
            // return ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_16_R3/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/v1_16_R3/entity/CraftPlayer getHandle ()Lnet/minecraft/server/v1_16_R3/EntityPlayer;
                        getfield net/minecraft/server/v1_16_R3/EntityPlayer playerConnection Lnet/minecraft/server/v1_16_R3/PlayerConnection;
                        getfield net/minecraft/server/v1_16_R3/PlayerConnection networkManager Lnet/minecraft/server/v1_16_R3/NetworkManager;
                        getfield net/minecraft/server/v1_16_R3/NetworkManager channel Lio/netty/channel/Channel;
                        areturn
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeNbt((CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(nbt));
            String asm = """
                    A:
                        new net/minecraft/server/v1_16_R3/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/server/v1_16_R3/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokestatic org/by1337/blib/BLib getApi ()Lorg/by1337/blib/Api;
                        invokeinterface org/by1337/blib/Api getParseCompoundTag ()Lorg/by1337/blib/nbt/ParseCompoundTag;
                        aload 1
                        invokeinterface org/by1337/blib/nbt/ParseCompoundTag toNMS (Lorg/by1337/blib/nbt/NBT;)Ljava/lang/Object;
                        checkcast net/minecraft/server/v1_16_R3/NBTTagCompound
                        invokevirtual net/minecraft/server/v1_16_R3/PacketDataSerializer a (Lnet/minecraft/server/v1_16_R3/NBTTagCompound;)Lnet/minecraft/server/v1_16_R3/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

    }

    private static class NmsAccessorV1_17_1 implements NmsAccessor {

        @Override
        @ASM
        public int getBlockId(BlockData blockData) {
            // return Block.getId(((CraftBlockData) blockData).getState());
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_17_R1/block/data/CraftBlockData
                        invokevirtual org/bukkit/craftbukkit/v1_17_R1/block/data/CraftBlockData getState ()Lnet/minecraft/world/level/block/state/IBlockData;
                        invokestatic net/minecraft/world/level/block/Block getCombinedId (Lnet/minecraft/world/level/block/state/IBlockData;)I
                        ireturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            // var nms = CraftParticle.toNMS(particle, value);
            // var byteBuf = new FriendlyByteBuf(b);
            // byteBuf.writeVarInt(Registry.PARTICLE_TYPE.getId(nms.getType()));
            // nms.writeToNetwork(byteBuf);
            String asm = """
                    A:
                        aload 1
                        aload 2
                        invokestatic org/bukkit/craftbukkit/v1_17_R1/CraftParticle toNMS (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/core/particles/ParticleParam;
                        astore 4
                    B:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 3
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        dup
                        astore 5
                        getstatic net/minecraft/core/IRegistry ab Lnet/minecraft/core/IRegistry;
                        aload 4
                        invokeinterface net/minecraft/core/particles/ParticleParam getParticle ()Lnet/minecraft/core/particles/Particle;
                        invokevirtual net/minecraft/core/IRegistry getId (Ljava/lang/Object;)I
                        invokevirtual net/minecraft/network/PacketDataSerializer d (I)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    D:
                        aload 4
                        aload 5
                        invokeinterface net/minecraft/core/particles/ParticleParam a (Lnet/minecraft/network/PacketDataSerializer;)V
                    E:
                        return
                    F:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            // new FriendlyByteBuf(b).writeItem(CraftItemStack.asNMSCopy(itemStack));
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        aload 1
                        invokestatic org/bukkit/craftbukkit/v1_17_R1/inventory/CraftItemStack asNMSCopy (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/world/item/ItemStack;
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public Channel getChannel(Player player) {
            // return ((CraftPlayer) player).getHandle().connection.connection.channel;
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_17_R1/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/v1_17_R1/entity/CraftPlayer getHandle ()Lnet/minecraft/server/level/EntityPlayer;
                        getfield net/minecraft/server/level/EntityPlayer b Lnet/minecraft/server/network/PlayerConnection;
                        getfield net/minecraft/server/network/PlayerConnection a Lnet/minecraft/network/NetworkManager;
                        getfield net/minecraft/network/NetworkManager k Lio/netty/channel/Channel;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeNbt((CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(nbt));
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokestatic org/by1337/blib/BLib getApi ()Lorg/by1337/blib/Api;
                        invokeinterface org/by1337/blib/Api getParseCompoundTag ()Lorg/by1337/blib/nbt/ParseCompoundTag;
                        aload 1
                        invokeinterface org/by1337/blib/nbt/ParseCompoundTag toNMS (Lorg/by1337/blib/nbt/NBT;)Ljava/lang/Object;
                        checkcast net/minecraft/nbt/NBTTagCompound
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }
    }

    private static class NmsAccessorV1_18_2 implements NmsAccessor {

        @Override
        @ASM
        public int getBlockId(BlockData blockData) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_18_R2/block/data/CraftBlockData
                        invokevirtual org/bukkit/craftbukkit/v1_18_R2/block/data/CraftBlockData getState ()Lnet/minecraft/world/level/block/state/IBlockData;
                        invokestatic net/minecraft/world/level/block/Block i (Lnet/minecraft/world/level/block/state/IBlockData;)I
                        ireturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            String asm = """
                    A:
                        aload 1
                        aload 2
                        invokestatic org/bukkit/craftbukkit/v1_18_R2/CraftParticle toNMS (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/core/particles/ParticleParam;
                        astore 4
                    B:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 3
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        dup
                        astore 5
                        getstatic net/minecraft/core/IRegistry Z Lnet/minecraft/core/IRegistry;
                        aload 4
                        invokeinterface net/minecraft/core/particles/ParticleParam b ()Lnet/minecraft/core/particles/Particle;
                        invokevirtual net/minecraft/core/IRegistry a (Ljava/lang/Object;)I
                        invokevirtual net/minecraft/network/PacketDataSerializer d (I)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    D:
                        aload 4
                        aload 5
                        invokeinterface net/minecraft/core/particles/ParticleParam a (Lnet/minecraft/network/PacketDataSerializer;)V
                    E:
                        return
                    F:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        aload 1
                        invokestatic org/bukkit/craftbukkit/v1_18_R2/inventory/CraftItemStack asNMSCopy (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/world/item/ItemStack;
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public Channel getChannel(Player player) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_18_R2/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/v1_18_R2/entity/CraftPlayer getHandle ()Lnet/minecraft/server/level/EntityPlayer;
                        getfield net/minecraft/server/level/EntityPlayer b Lnet/minecraft/server/network/PlayerConnection;
                        getfield net/minecraft/server/network/PlayerConnection a Lnet/minecraft/network/NetworkManager;
                        getfield net/minecraft/network/NetworkManager m Lio/netty/channel/Channel;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeNbt((CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(nbt));
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokestatic org/by1337/blib/BLib getApi ()Lorg/by1337/blib/Api;
                        invokeinterface org/by1337/blib/Api getParseCompoundTag ()Lorg/by1337/blib/nbt/ParseCompoundTag;
                        aload 1
                        invokeinterface org/by1337/blib/nbt/ParseCompoundTag toNMS (Lorg/by1337/blib/nbt/NBT;)Ljava/lang/Object;
                        checkcast net/minecraft/nbt/NBTTagCompound
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }
    }

    private static class NmsAccessorV1_19_4 implements NmsAccessor {

        @Override
        @ASM
        public int getBlockId(BlockData blockData) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_19_R3/block/data/CraftBlockData
                        invokevirtual org/bukkit/craftbukkit/v1_19_R3/block/data/CraftBlockData getState ()Lnet/minecraft/world/level/block/state/IBlockData;
                        invokestatic net/minecraft/world/level/block/Block i (Lnet/minecraft/world/level/block/state/IBlockData;)I
                        ireturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            String asm = """
                    A:
                        aload 1
                        aload 2
                        invokestatic org/bukkit/craftbukkit/v1_19_R3/CraftParticle toNMS (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/core/particles/ParticleParam;
                        astore 4
                    B:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 3
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        dup
                        astore 5
                        getstatic net/minecraft/core/registries/BuiltInRegistries k Lnet/minecraft/core/IRegistry;
                        aload 4
                        invokeinterface net/minecraft/core/particles/ParticleParam b ()Lnet/minecraft/core/particles/Particle;
                        invokeinterface net/minecraft/core/IRegistry a (Ljava/lang/Object;)I
                        invokevirtual net/minecraft/network/PacketDataSerializer d (I)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    D:
                        aload 4
                        aload 5
                        invokeinterface net/minecraft/core/particles/ParticleParam a (Lnet/minecraft/network/PacketDataSerializer;)V
                    E:
                        return
                    F:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        aload 1
                        invokestatic org/bukkit/craftbukkit/v1_19_R3/inventory/CraftItemStack asNMSCopy (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/world/item/ItemStack;
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public Channel getChannel(Player player) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_19_R3/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/v1_19_R3/entity/CraftPlayer getHandle ()Lnet/minecraft/server/level/EntityPlayer;
                        getfield net/minecraft/server/level/EntityPlayer b Lnet/minecraft/server/network/PlayerConnection;
                        getfield net/minecraft/server/network/PlayerConnection h Lnet/minecraft/network/NetworkManager;
                        getfield net/minecraft/network/NetworkManager m Lio/netty/channel/Channel;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeNbt((CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(nbt));
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokestatic org/by1337/blib/BLib getApi ()Lorg/by1337/blib/Api;
                        invokeinterface org/by1337/blib/Api getParseCompoundTag ()Lorg/by1337/blib/nbt/ParseCompoundTag;
                        aload 1
                        invokeinterface org/by1337/blib/nbt/ParseCompoundTag toNMS (Lorg/by1337/blib/nbt/NBT;)Ljava/lang/Object;
                        checkcast net/minecraft/nbt/NBTTagCompound
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }
    }

    private static class NmsAccessorV1_20_4 implements NmsAccessor {

        @Override
        @ASM
        public int getBlockId(BlockData blockData) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_20_R3/block/data/CraftBlockData
                        invokevirtual org/bukkit/craftbukkit/v1_20_R3/block/data/CraftBlockData getState ()Lnet/minecraft/world/level/block/state/IBlockData;
                        invokestatic net/minecraft/world/level/block/Block i (Lnet/minecraft/world/level/block/state/IBlockData;)I
                        ireturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            String asm = """
                    A:
                        aload 1
                        aload 2
                        invokestatic org/bukkit/craftbukkit/v1_20_R3/CraftParticle convertLegacy (Ljava/lang/Object;)Ljava/lang/Object;
                        invokestatic org/bukkit/craftbukkit/v1_20_R3/CraftParticle createParticleParam (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/core/particles/ParticleParam;
                        astore 4
                    B:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 3
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        dup
                        astore 5
                    
                        getstatic net/minecraft/core/registries/BuiltInRegistries j Lnet/minecraft/core/IRegistry;
                        aload 4
                        invokeinterface net/minecraft/core/particles/ParticleParam b ()Lnet/minecraft/core/particles/Particle;
                        invokeinterface net/minecraft/core/IRegistry a (Ljava/lang/Object;)I
                        invokevirtual net/minecraft/network/PacketDataSerializer c (I)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    D:
                        aload 4
                        aload 5
                        invokeinterface net/minecraft/core/particles/ParticleParam a (Lnet/minecraft/network/PacketDataSerializer;)V
                    E:
                        return
                    F:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        aload 1
                        invokestatic org/bukkit/craftbukkit/v1_20_R3/inventory/CraftItemStack asNMSCopy (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/world/item/ItemStack;
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public Channel getChannel(Player player) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_20_R3/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/v1_20_R3/entity/CraftPlayer getHandle ()Lnet/minecraft/server/level/EntityPlayer;
                        getfield net/minecraft/server/level/EntityPlayer c Lnet/minecraft/server/network/PlayerConnection;
                        getfield net/minecraft/server/network/PlayerConnection c Lnet/minecraft/network/NetworkManager;
                        getfield net/minecraft/network/NetworkManager n Lio/netty/channel/Channel;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeNbt((CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(nbt));
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokestatic org/by1337/blib/BLib getApi ()Lorg/by1337/blib/Api;
                        invokeinterface org/by1337/blib/Api getParseCompoundTag ()Lorg/by1337/blib/nbt/ParseCompoundTag;
                        aload 1
                        invokeinterface org/by1337/blib/nbt/ParseCompoundTag toNMS (Lorg/by1337/blib/nbt/NBT;)Ljava/lang/Object;
                        checkcast net/minecraft/nbt/NBTTagCompound
                        invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/nbt/NBTBase;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeComponent(Component component, ByteBuf b) {
            // source
            // new FriendlyByteBuf(b).writeComponent(component);
            String asm = """
                    A:
                        new net/minecraft/network/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        aload 1
                        invokevirtual net/minecraft/network/PacketDataSerializer writeComponent (Lnet/kyori/adventure/text/Component;)Lnet/minecraft/network/PacketDataSerializer;
                        pop
                    B:
                        return
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }
    }

    private static class NmsAccessorV1_20_1 implements NmsAccessor {

        @Override
        @ASM
        public int getBlockId(BlockData blockData) {
            String asm = """
                    A:
                    	aload 1
                    	checkcast org/bukkit/craftbukkit/v1_20_R1/block/data/CraftBlockData
                    	invokevirtual org/bukkit/craftbukkit/v1_20_R1/block/data/CraftBlockData getState ()Lnet/minecraft/world/level/block/state/IBlockData;
                    	invokestatic net/minecraft/world/level/block/Block i (Lnet/minecraft/world/level/block/state/IBlockData;)I
                    	ireturn
                    B:""";
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            String asm = """
                    A:
                    	aload 1
                    	aload 2
                    	invokestatic org/bukkit/craftbukkit/v1_20_R1/CraftParticle toNMS (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/core/particles/ParticleParam;
                    	astore 4
                    B:
                    	new net/minecraft/network/PacketDataSerializer
                    	dup
                    	aload 3
                    	invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                    	dup
                    	astore 5
                    	getstatic net/minecraft/core/registries/BuiltInRegistries k Lnet/minecraft/core/IRegistry;
                    	aload 4
                    	invokeinterface net/minecraft/core/particles/ParticleParam b ()Lnet/minecraft/core/particles/Particle;
                    	invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/core/Registry;Ljava/lang/Object;)V
                    D:
                    	aload 4
                    	aload 5
                    	invokeinterface net/minecraft/core/particles/ParticleParam a (Lnet/minecraft/network/PacketDataSerializer;)V
                    E:
                    	return
                    F:""";
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            String asm = """
                    A:
                    	new net/minecraft/network/PacketDataSerializer
                    	dup
                    	aload 2
                    	invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                    	aload 1
                    	invokestatic org/bukkit/craftbukkit/v1_20_R1/inventory/CraftItemStack asNMSCopy (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/world/item/ItemStack;
                    	invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/PacketDataSerializer;
                    	pop
                    B:
                    	return
                    C:""";
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public Channel getChannel(Player player) {
            String asm = """
                    A:
                    	aload 1
                    	checkcast org/bukkit/craftbukkit/v1_20_R1/entity/CraftPlayer
                    	invokevirtual org/bukkit/craftbukkit/v1_20_R1/entity/CraftPlayer getHandle ()Lnet/minecraft/server/level/EntityPlayer;
                    	getfield net/minecraft/server/level/EntityPlayer c Lnet/minecraft/server/network/PlayerConnection;
                    	getfield net/minecraft/server/network/PlayerConnection h Lnet/minecraft/network/NetworkManager;
                    	getfield net/minecraft/network/NetworkManager m Lio/netty/channel/Channel;
                    	areturn
                    B:""";
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            String asm = """
                    A:
                    	new net/minecraft/network/PacketDataSerializer
                    	dup
                    	aload 2
                    	invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                    	invokestatic org/by1337/blib/BLib getApi ()Lorg/by1337/blib/Api;
                    	invokeinterface org/by1337/blib/Api getParseCompoundTag ()Lorg/by1337/blib/nbt/ParseCompoundTag;
                    	aload 1
                    	invokeinterface org/by1337/blib/nbt/ParseCompoundTag toNMS (Lorg/by1337/blib/nbt/NBT;)Ljava/lang/Object;
                    	checkcast net/minecraft/nbt/NBTTagCompound
                    	invokevirtual net/minecraft/network/PacketDataSerializer a (Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/network/PacketDataSerializer;
                    	pop
                    B:
                    	return
                    C:""";
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        @ASM
        public void writeComponent(Component component, ByteBuf b) {
            String asm = """
                    A:
                    	new net/minecraft/network/PacketDataSerializer
                    	dup
                    	aload 2
                    	invokespecial net/minecraft/network/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                    	aload 1
                    	invokevirtual net/minecraft/network/PacketDataSerializer writeComponent (Lnet/kyori/adventure/text/Component;)Lnet/minecraft/network/PacketDataSerializer;
                    	pop
                    B:
                    	return
                    C:""";
            throw new IllegalStateException("ASM did not apply! " + asm);
        }
        //     public int getBlockId(BlockData blockData) {
        //        return Block.getId(((CraftBlockData) blockData).getState());
        //    }
        //
        //    public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
        //        var v = CraftParticle.toNMS(particle, value);
        //        var v1 = new FriendlyByteBuf(b);
        //        v1.writeId(BuiltInRegistries.PARTICLE_TYPE, v.getType());
        //        v.writeToNetwork(v1);
        //    }
        //
        //    public void writeItemStack(ItemStack itemStack, ByteBuf b) {
        //        new FriendlyByteBuf(b).writeItem(CraftItemStack.asNMSCopy(itemStack));
        //    }
        //
        //    public Channel getChannel(Player player) {
        //        return ((CraftPlayer) player).getHandle().connection.connection.channel;
        //    }
        //
        //    public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
        //        new FriendlyByteBuf(b).writeNbt((net.minecraft.nbt.CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(compoundTag));
        //    }
        //
        //    public void writeComponent(net.kyori.adventure.text.Component component, ByteBuf b) {
        //        new FriendlyByteBuf(b).writeComponent(component);
        //    }
    }

    private static class NmsAccessorV1_20_6 implements NmsAccessor {
        private static final Object PARTICLE_CODEC = getCodec("PARTICLE");
        private static final Object PARTICLES_CODEC = getCodec("PARTICLES");
        private static final Object COMPOUND_TAG_CODEC = getCodec("COMPOUND_TAG");
        private static final Object ITEM_STACK_CODEC = getCodec("ITEM_STACK");
        private static final Object COMPONENT_CODEC = getCodec("COMPONENT");


        @ASM
        private void write(Object codec, ByteBuf byteBuf, Object value) {
            String asm = """
                    A:
                        aload 1
                        checkcast net/minecraft/network/codec/StreamEncoder
                        new net/minecraft/network/RegistryFriendlyByteBuf
                        dup
                        aload 2
                        invokestatic net/minecraft/server/MinecraftServer getServer ()Lnet/minecraft/server/MinecraftServer;
                        invokevirtual net/minecraft/server/MinecraftServer registryAccess ()Lnet/minecraft/core/RegistryAccess$Frozen;
                        invokespecial net/minecraft/network/RegistryFriendlyByteBuf <init> (Lio/netty/buffer/ByteBuf;Lnet/minecraft/core/RegistryAccess;)V
                        aload 3
                        invokeinterface net/minecraft/network/codec/StreamEncoder encode (Ljava/lang/Object;Ljava/lang/Object;)V
                    B:
                        return
                    C:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @ASM
        private Object toNMSParticle(Particle particle, Object val) {
            String asm = """
                    A:
                        aload 1
                        aload 2
                        invokestatic org/bukkit/craftbukkit/CraftParticle createParticleParam (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/core/particles/ParticleOptions;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @ASM
        private Object toNMSItemsStack(ItemStack itemStack) {
            String asm = """
                    A:
                        aload 1
                        invokestatic org/bukkit/craftbukkit/inventory/CraftItemStack unwrap (Lorg/bukkit/inventory/ItemStack;)Lnet/minecraft/world/item/ItemStack;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @ASM
        private Object toNMSComponent(Component component) {
            String asm = """
                    A:
                        aload 1
                        invokestatic io/papermc/paper/adventure/PaperAdventure asVanilla (Lnet/kyori/adventure/text/Component;)Lnet/minecraft/network/chat/Component;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        private static Object getCodec(String entityDataSerializer) {
            try {
                Class<?> cl = Class.forName("net.minecraft.network.syncher.EntityDataSerializers");
                Field field = cl.getDeclaredField(entityDataSerializer);
                field.setAccessible(true);
                Object val = field.get(null);

                Class<?> cl2 = Class.forName("net.minecraft.network.syncher.EntityDataSerializer");
                Method method = cl2.getDeclaredMethod("codec");
                method.setAccessible(true);
                return method.invoke(val);
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }

        }

        @ASM
        @Override
        public int getBlockId(BlockData blockData) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/block/data/CraftBlockData
                        invokevirtual org/bukkit/craftbukkit/block/data/CraftBlockData getState ()Lnet/minecraft/world/level/block/state/BlockState;
                        invokestatic net/minecraft/world/level/block/Block getId (Lnet/minecraft/world/level/block/state/BlockState;)I
                        ireturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            write(PARTICLE_CODEC, b, toNMSParticle(particle, value));
        }

        @Override
        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            write(ITEM_STACK_CODEC, b, toNMSItemsStack(itemStack));
        }

        @Override
        public void writeParticles(List<ParticleOptions<?>> list, ByteBuf b) {
            write(PARTICLES_CODEC, b, list.stream().map(p -> toNMSParticle(p.particle(), p.value())).toList());
        }

        @Override
        public void writeComponent(Component component, ByteBuf b) {
            write(COMPONENT_CODEC, b, toNMSComponent(component));
        }

        @ASM
        @Override
        public Channel getChannel(Player player) {
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/entity/CraftPlayer getHandle ()Lnet/minecraft/server/level/ServerPlayer;
                        getfield net/minecraft/server/level/ServerPlayer connection Lnet/minecraft/server/network/ServerGamePacketListenerImpl;
                        getfield net/minecraft/server/network/ServerGamePacketListenerImpl connection Lnet/minecraft/network/Connection;
                        getfield net/minecraft/network/Connection channel Lio/netty/channel/Channel;
                        areturn
                    B:
                    """;
            throw new IllegalStateException("ASM did not apply! " + asm);
        }

        @Override
        public void writeCompoundTag(CompoundTag compoundTag, ByteBuf b) {
            write(COMPOUND_TAG_CODEC, b, BLib.getApi().getParseCompoundTag().toNMS(compoundTag));
        }
    }

    //    private static Color fromARGB0(int argb) {
    //        return Color.fromARGB(argb);
    //    }
    //
    //    int getBlockId(BlockData blockData) {
    //        return Block.getId(((CraftBlockData) blockData).getState());
    //    }
    //
    //    void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
    //        var nms = CraftParticle.createParticleParam(particle, CraftParticle.convertLegacy(value));
    //        var byteBuf = new FriendlyByteBuf(b);
    //        byteBuf.writeVarInt(BuiltInRegistries.PARTICLE_TYPE.getId(nms.getType()));
    //        nms.writeToNetwork(byteBuf);
    //    }
    //
    //    void writeItemStack(ItemStack itemStack, ByteBuf b) {
    //        new FriendlyByteBuf(b).writeItem(CraftItemStack.asNMSCopy(itemStack));
    //    }
    //
    //    Channel getChannel(Player player) {
    //        return ((CraftPlayer) player).getHandle().connection.connection.channel;
    //    }
    //    public void writeCompoundTag(org.by1337.blib.nbt.impl.CompoundTag nbt, ByteBuf b) {
    //       new FriendlyByteBuf(b).writeNbt((CompoundTag) BLib.getApi().getParseCompoundTag().toNMS(nbt));
    //    }
}
