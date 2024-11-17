package dev.by1337.virtualentity.core.nms;

import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.annotations.ASM;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
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

    public static Channel getChannel(Player player) {
        return accessor.getChannel(player);
    }

    private interface NmsAccessor {
        int getCombinedId(BlockData blockData);

        void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b);

        void writeItemStack(ItemStack itemStack, ByteBuf b);

        Channel getChannel(Player player);
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
}
