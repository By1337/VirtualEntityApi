package dev.by1337.virtualentity.core;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.tracker.PlayerTracker;
import dev.by1337.virtualentity.api.virtual.VirtualArmorStand;
import dev.by1337.virtualentity.api.virtual.VirtualCreeper;
import dev.by1337.virtualentity.api.virtual.VirtualItem;
import dev.by1337.virtualentity.core.mappings.VirtualEntityRegistrar;
import dev.by1337.virtualentity.core.network.Packet;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.blib.geom.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        VirtualEntityRegistrar.register();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            if (args[0].equals("debug")) {
                Packet.debug = !Packet.debug;
                return true;
            }
        }
        Player player = (Player) sender;

        PlayerTracker tracker = new PlayerTracker(player.getWorld(), new Vec3d(player.getLocation()));

        VirtualArmorStand armorStand = VirtualEntityApi.getFactory().create(VirtualEntityType.ARMOR_STAND, VirtualArmorStand.class);
        final Vec3d center = new Vec3d(player.getLocation());
        final Vec3d spawnPos = center.add(0, 0, 0);
        armorStand.setPos(spawnPos);
        armorStand.setEquipment(EquipmentSlot.HEAD, new ItemStack(Material.RED_SHULKER_BOX));
        tracker.addEntity(armorStand);

        VirtualCreeper creeper = VirtualEntityApi.getFactory().create(VirtualEntityType.CREEPER, VirtualCreeper.class);
        creeper.setPos(new Vec3d(player.getLocation()));
        creeper.lookAt(armorStand.getPos());
        creeper.setPowered(true);
        tracker.addEntity(creeper);

        new BukkitRunnable() {
            Vec3d vec = new Vec3d(0, 0, (360D / 7D) / 7);
            int tick = 0;
            final Random random = new Random();

            @Override
            public void run() {
                if (!player.isOnline() || tick >= 200) {
                    cancel();
                    tracker.removeAll();
                    return;
                }
                vec = vec.rotateAroundY(Math.toRadians(5));
                var pos = spawnPos.add(vec);
                armorStand.lookAt(pos);
                armorStand.setPos(pos);
                var loc = pos.toLocation(player.getWorld());
                player.getWorld().spawnParticle(Particle.FLAME, loc, 0);
                creeper.lookAt(armorStand.getPos());
                tracker.tick();
                if (tick % 5 == 0) {
                    VirtualItem item = VirtualEntityApi.getFactory().create(VirtualEntityType.ITEM, VirtualItem.class);
                    item.setPos(armorStand.getPos());
                    item.setItem(new ItemStack(Material.values()[random.nextInt(50)]));
                    tracker.addEntity(item);
                }
                tick++;
            }
        }.runTaskTimerAsynchronously(this, 0, 1);

        return true;
    }
}
