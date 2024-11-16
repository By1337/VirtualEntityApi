package dev.by1337.virtualentity.core.nms;

import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.annotations.ASM;
import io.netty.buffer.ByteBuf;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NmsUtil {
    private static final NmsAccessor accessor = new NmsAccessorV1_16_5();

    public static int getCombinedId(BlockData blockData) {
        return accessor.getCombinedId(blockData);
    }

    public static void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
        accessor.writeParticleOptions(particleOptions, b);
    }

    public static void writeItemStack(ItemStack itemStack, ByteBuf b) {
        accessor.writeItemStack(itemStack, b);
    }

    public static void send(Player player, ByteBuf byteBuf) {
        accessor.send(player, byteBuf);
    }

    private interface NmsAccessor {
        int getCombinedId(BlockData blockData);

        void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b);

        void writeItemStack(ItemStack itemStack, ByteBuf b);
        void send(Player player, ByteBuf byteBuf);
    }

    private static class NmsAccessorV1_16_5 implements NmsAccessor {

        @Override
        @ASM
        public int getCombinedId(BlockData blockData) {
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
            return 0;
        }

        @Override
        @ASM
        public void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
            // source
            // var nms = CraftParticle.toNMS(particleOptions.particle(), particleOptions.value());
            // ByteBuffCodecs.VAR_INT.accept(Registry.PARTICLE_TYPE.getId(nms.getParticle()), b);
            // nms.writeToNetwork(new FriendlyByteBuf(b));
            String asm = """
                    A:
                        aload 1
                        invokevirtual dev/by1337/virtualentity/api/ParticleOptions particle ()Lorg/bukkit/Particle;
                        aload 1
                        invokevirtual dev/by1337/virtualentity/api/ParticleOptions value ()Ljava/lang/Object;
                        invokestatic org/bukkit/craftbukkit/v1_16_R3/CraftParticle toNMS (Lorg/bukkit/Particle;Ljava/lang/Object;)Lnet/minecraft/server/v1_16_R3/ParticleParam;
                        astore 3
                    B:
                        getstatic dev/by1337/virtualentity/core/network/ByteBuffCodecs VAR_INT Ljava/util/function/BiConsumer;
                        getstatic net/minecraft/server/v1_16_R3/IRegistry PARTICLE_TYPE Lnet/minecraft/server/v1_16_R3/IRegistry;
                        aload 3
                        invokeinterface net/minecraft/server/v1_16_R3/ParticleParam getParticle ()Lnet/minecraft/server/v1_16_R3/Particle;
                        invokevirtual net/minecraft/server/v1_16_R3/IRegistry a (Ljava/lang/Object;)I
                        invokestatic java/lang/Integer valueOf (I)Ljava/lang/Integer;
                        aload 2
                        invokeinterface java/util/function/BiConsumer accept (Ljava/lang/Object;Ljava/lang/Object;)V
                    C:
                        aload 3
                        new net/minecraft/server/v1_16_R3/PacketDataSerializer
                        dup
                        aload 2
                        invokespecial net/minecraft/server/v1_16_R3/PacketDataSerializer <init> (Lio/netty/buffer/ByteBuf;)V
                        invokeinterface net/minecraft/server/v1_16_R3/ParticleParam a (Lnet/minecraft/server/v1_16_R3/PacketDataSerializer;)V
                    D:
                        return
                    """;
            System.out.println(asm);
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
        }

        @Override
        @ASM
        public void send(Player player, ByteBuf byteBuf) {
            // source
            // ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel.writeAndFlush((Object)byteBuf);
            String asm = """
                    A:
                        aload 1
                        checkcast org/bukkit/craftbukkit/v1_16_R3/entity/CraftPlayer
                        invokevirtual org/bukkit/craftbukkit/v1_16_R3/entity/CraftPlayer getHandle ()Lnet/minecraft/server/v1_16_R3/EntityPlayer;
                        getfield net/minecraft/server/v1_16_R3/EntityPlayer playerConnection Lnet/minecraft/server/v1_16_R3/PlayerConnection;
                        getfield net/minecraft/server/v1_16_R3/PlayerConnection networkManager Lnet/minecraft/server/v1_16_R3/NetworkManager;
                        getfield net/minecraft/server/v1_16_R3/NetworkManager channel Lio/netty/channel/Channel;
                        aload 2
                        invokeinterface io/netty/channel/Channel writeAndFlush (Ljava/lang/Object;)Lio/netty/channel/ChannelFuture;
                        pop
                    B:
                        return
                    """;
            System.out.println(asm);
        }
    }
}
