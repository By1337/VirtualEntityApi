package dev.by1337.virtualentity.core.nms;

import dev.by1337.core.util.network.ChannelGetter;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.kyori.adventure.text.Component;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import static java.lang.invoke.MethodType.methodType;

public final class NmsUtil {
    private NmsUtil() {
    }

    public static int getCombinedId(BlockData blockData) {
        try {
            return (int) Bindings.BLOCK_ID.invokeExact(blockData);
        } catch (Throwable t) {
            throw failure("resolve block state ID", t);
        }
    }

    public static void writeParticleOptions(ParticleOptions<?> particleOptions, ByteBuf b) {
        try {
            Object particle = (Object) Bindings.PARTICLE.invokeExact(particleOptions.particle(), particleOptions.value());
            write(Bindings.WRITE_PARTICLE, b, particle);
        } catch (Throwable t) {
            throw failure("write particle", t);
        }
    }

    public static void writeItemStack(ItemStack itemStack, ByteBuf b) {
        try {
            Object item = (Object) Bindings.ITEM_STACK.invokeExact(itemStack);
            write(Bindings.WRITE_ITEM_STACK, b, item);
        } catch (Throwable t) {
            throw failure("write item stack", t);
        }
    }

    public static Channel getChannel(Player player) {
        return ChannelGetter.get(player);
    }

    public static void writeComponent(Component component, ByteBuf b) {
        try {
            Object nativeComponent = (Object) Bindings.COMPONENT.invokeExact(component);
            write(Bindings.WRITE_COMPONENT, b, nativeComponent);
        } catch (Throwable t) {
            throw failure("write component", t);
        }
    }

    public static void writeParticles(List<ParticleOptions<?>> list, ByteBuf b) {
        try {
            List<Object> particles = new ArrayList<>(list.size());
            for (ParticleOptions<?> particle : list) {
                particles.add((Object) Bindings.PARTICLE.invokeExact(particle.particle(), particle.value()));
            }
            write(Bindings.WRITE_PARTICLES, b, particles);
        } catch (Throwable t) {
            throw failure("write particle list", t);
        }
    }

    private static void write(MethodHandle encoder, ByteBuf b, Object value) throws Throwable {
        // Obtain current registries on each write; only method bindings are cached.
        Object registries = (Object) Bindings.REGISTRY_ACCESS.invokeExact();
        Object buffer = (Object) Bindings.REGISTRY_BUFFER.invokeExact(b, registries);
        encoder.invokeExact(buffer, value);
    }

    private static IllegalStateException failure(String operation, Throwable cause) {
        if (cause instanceof Error error) throw error;
        return new IllegalStateException("Unable to " + operation + " using native Minecraft codecs", cause);
    }

    /** Resolved once, lazily on the first native operation, using Mojang's mapped signatures. */
    private static final class Bindings {
        private static final MethodHandle REGISTRY_ACCESS;
        private static final MethodHandle REGISTRY_BUFFER;
        private static final MethodHandle PARTICLE;
        private static final MethodHandle ITEM_STACK;
        private static final MethodHandle COMPONENT;
        private static final MethodHandle BLOCK_ID;
        private static final MethodHandle WRITE_PARTICLE;
        private static final MethodHandle WRITE_PARTICLES;
        private static final MethodHandle WRITE_ITEM_STACK;
        private static final MethodHandle WRITE_COMPONENT;

        static {
            try {
                MethodHandles.Lookup lookup = MethodHandles.publicLookup();
                Class<?> server = Class.forName("net.minecraft.server.MinecraftServer");
                Class<?> registryAccess = Class.forName("net.minecraft.core.RegistryAccess");
                Class<?> frozenRegistries = Class.forName("net.minecraft.core.RegistryAccess$Frozen");
                Class<?> registryBuffer = Class.forName("net.minecraft.network.RegistryFriendlyByteBuf");
                Class<?> streamEncoder = Class.forName("net.minecraft.network.codec.StreamEncoder");
                Class<?> streamCodec = Class.forName("net.minecraft.network.codec.StreamCodec");
                Class<?> serializer = Class.forName("net.minecraft.network.syncher.EntityDataSerializer");
                Class<?> serializers = Class.forName("net.minecraft.network.syncher.EntityDataSerializers");
                Class<?> blockState = Class.forName("net.minecraft.world.level.block.state.BlockState");

                MethodHandle getServer = lookup.findStatic(server, "getServer", methodType(server));
                MethodHandle getRegistries = lookup.findVirtual(server, "registryAccess", methodType(frozenRegistries));
                REGISTRY_ACCESS = MethodHandles.filterReturnValue(getServer, getRegistries)
                        .asType(methodType(Object.class));
                REGISTRY_BUFFER = lookup.findConstructor(registryBuffer, methodType(void.class, ByteBuf.class, registryAccess))
                        .asType(methodType(Object.class, ByteBuf.class, Object.class));

                PARTICLE = lookup.findStatic(Class.forName("org.bukkit.craftbukkit.CraftParticle"), "createParticleParam",
                                methodType(Class.forName("net.minecraft.core.particles.ParticleOptions"), Particle.class, Object.class))
                        .asType(methodType(Object.class, Particle.class, Object.class));
                ITEM_STACK = lookup.findStatic(Class.forName("org.bukkit.craftbukkit.inventory.CraftItemStack"), "unwrap",
                                methodType(Class.forName("net.minecraft.world.item.ItemStack"), ItemStack.class))
                        .asType(methodType(Object.class, ItemStack.class));
                COMPONENT = lookup.findStatic(Class.forName("io.papermc.paper.adventure.PaperAdventure"), "asVanilla",
                                methodType(Class.forName("net.minecraft.network.chat.Component"), Component.class))
                        .asType(methodType(Object.class, Component.class));

                MethodHandle getState = lookup.findVirtual(Class.forName("org.bukkit.craftbukkit.block.data.CraftBlockData"),
                        "getState", methodType(blockState));
                MethodHandle getId = lookup.findStatic(Class.forName("net.minecraft.world.level.block.Block"),
                        "getId", methodType(int.class, blockState));
                BLOCK_ID = MethodHandles.filterReturnValue(getState, getId).asType(methodType(int.class, BlockData.class));

                MethodHandle encode = lookup.findVirtual(streamEncoder, "encode",
                        methodType(void.class, Object.class, Object.class));
                MethodHandle codec = lookup.findVirtual(serializer, "codec", methodType(streamCodec))
                        .asType(methodType(Object.class, Object.class));
                WRITE_PARTICLE = encoder(lookup, serializers, serializer, codec, encode, "PARTICLE");
                WRITE_PARTICLES = encoder(lookup, serializers, serializer, codec, encode, "PARTICLES");
                WRITE_ITEM_STACK = encoder(lookup, serializers, serializer, codec, encode, "ITEM_STACK");
                WRITE_COMPONENT = encoder(lookup, serializers, serializer, codec, encode, "COMPONENT");
            } catch (Throwable t) {
                throw new ExceptionInInitializerError(new IllegalStateException("Cannot bind native Minecraft codecs", t));
            }
        }

        private static MethodHandle encoder(MethodHandles.Lookup lookup, Class<?> serializers, Class<?> serializer,
                                            MethodHandle codec, MethodHandle encode, String name) throws Throwable {
            MethodHandle getter = lookup.findStaticGetter(serializers, name, serializer).asType(methodType(Object.class));
            Object value = (Object) getter.invokeExact();
            Object nativeCodec = (Object) codec.invokeExact(value);
            return encode.bindTo(nativeCodec);
        }
    }
}
