package dev.by1337.virtualentity.dumper;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamEncoder;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.level.block.Block;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftParticle;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.by1337.blib.BLib;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class Main extends JavaPlugin {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            if (args.length == 0) {
                getDataFolder().mkdirs();
                MappingsCreator.create(getDataFolder().toPath());
                sender.sendMessage("done");
            } else {
                File dump = new File(getDataFolder(), "dump");
                dump.mkdirs();
                EasyEntityDumper.dump(dump);
            }

        } catch (Throwable e) {
            getSLF4JLogger().error("Failed to create mappings file", e);
        }
        return true;
    }

    public Object toNMS(Component component) {
        return PaperAdventure.asVanilla(component);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void write(Object codec, ByteBuf byteBuf, Object value) {
        ((StreamEncoder) codec).encode(new RegistryFriendlyByteBuf(byteBuf, MinecraftServer.getServer().registryAccess()), value);
    }

    public Object toNMS(Particle particle, Object val){
        return CraftParticle.createParticleParam(particle, val);
    }
    public Object toNMS(ItemStack itemStack){
        return CraftItemStack.unwrap(itemStack);
    }

    int getBlockId(BlockData blockData) {
        return Block.getId(((CraftBlockData) blockData).getState());
    }

    Channel getChannel(Player player) {
        return ((CraftPlayer) player).getHandle().connection.connection.channel;
    }
}
