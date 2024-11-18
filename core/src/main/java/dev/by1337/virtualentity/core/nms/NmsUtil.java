package dev.by1337.virtualentity.core.nms;

import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.annotations.ASM;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;

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

    static {
        accessor = switch (Version.VERSION){
            case V1_16_5 -> new NmsAccessorV1_16_5();
            case V1_17_1 -> new NmsAccessorV1_17_1();
            case V1_18_2 -> new NmsAccessorV1_18_2();
            default -> throw new UnsupportedOperationException("Unsupported version " + Version.VERSION);
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
        }

        @Override
        @ASM
        public void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
            // source
            // var nms = CraftParticle.toNMS(particleOptions.particle(), particleOptions.value());
            // ByteBuffUtil.writeVarInt(Registry.PARTICLE_TYPE.getId(nms.getParticle()), b);
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
                        invokestatic dev/by1337/virtualentity/core/network/ByteBuffUtil writeVarInt (ILio/netty/buffer/ByteBuf;)V
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
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
            System.out.println(asm);
            throw new IllegalStateException("ASM did not apply!");
        }
    }
}
