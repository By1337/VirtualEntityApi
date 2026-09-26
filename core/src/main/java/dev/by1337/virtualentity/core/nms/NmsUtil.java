package dev.by1337.virtualentity.core.nms;

import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.core.annotations.ASM;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.kyori.adventure.text.Component;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class NmsUtil {
    private static final NativeCodecs accessor = new NativeCodecs();

    public static int getCombinedId(BlockData blockData) {
        return accessor.getBlockId(blockData);
    }

    public static void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
        accessor.writeParticle(particleOptions.particle(), particleOptions.value(), b);
    }

    public static void writeItemStack(ItemStack itemStack, ByteBuf b) {
        accessor.writeItemStack(itemStack, b);
    }

    public static Channel getChannel(Player player) {
        return accessor.getChannel(player);
    }

    public static void writeComponent(Component component, ByteBuf b) {
        accessor.writeComponent(component, b);
    }

    public static void writeParticles(List<ParticleOptions<?>> list, ByteBuf b) {
        accessor.writeParticles(list, b);
    }

    private static class NativeCodecs {
        private static final Object PARTICLE_CODEC = getCodec("PARTICLE");
        private static final Object PARTICLES_CODEC = getCodec("PARTICLES");
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
                throw new IllegalStateException("Cannot load native metadata codec " + entityDataSerializer, t);
            }
        }

        @ASM
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

        public void writeParticle(Particle particle, @Nullable Object value, ByteBuf b) {
            write(PARTICLE_CODEC, b, toNMSParticle(particle, value));
        }

        public void writeItemStack(ItemStack itemStack, ByteBuf b) {
            write(ITEM_STACK_CODEC, b, toNMSItemsStack(itemStack));
        }

        public void writeParticles(List<ParticleOptions<?>> list, ByteBuf b) {
            write(PARTICLES_CODEC, b, list.stream().map(p -> toNMSParticle(p.particle(), p.value())).toList());
        }

        public void writeComponent(Component component, ByteBuf b) {
            write(COMPONENT_CODEC, b, toNMSComponent(component));
        }

        @ASM
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

    }

}
