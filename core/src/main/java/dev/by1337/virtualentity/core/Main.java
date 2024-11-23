package dev.by1337.virtualentity.core;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.api.tracker.PlayerTracker;
import dev.by1337.virtualentity.api.virtual.VirtualAreaEffectCloud;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.decoration.VirtualArmorStand;
import dev.by1337.virtualentity.api.virtual.item.VirtualItem;
import dev.by1337.virtualentity.api.virtual.monster.VirtualCreeper;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.mappings.VirtualEntityRegistrar;
import dev.by1337.virtualentity.core.network.Packet;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.by1337.blib.command.Command;
import org.by1337.blib.command.CommandWrapper;
import org.by1337.blib.command.argument.ArgumentEnumValue;
import org.by1337.blib.command.requires.RequiresPermission;
import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.util.Version;

import java.util.Random;
import java.util.Set;

public class Main extends JavaPlugin {

    private CommandWrapper commandWrapper;

    @Override
    public void onLoad() {
        Mappings.load();
        VirtualEntityRegistrar.register();
    }


    @Override
    public void onEnable() {
        commandWrapper = new CommandWrapper(createCommand(this), this);
        commandWrapper.setPermission("virtualentityapi.admin");
        commandWrapper.register();
    }

    @Override
    public void onDisable() {
        commandWrapper.close();

        Player player = null;
        // Создаём пакетный ArmorStand
        VirtualArmorStand armorStand = VirtualEntityApi.getFactory().create(VirtualEntityType.ARMOR_STAND, VirtualArmorStand.class);
        // устанавливаем параметры
        armorStand.setCustomName(Component.text("Custom name"));
        armorStand.setCustomNameVisible(true);
        armorStand.setPos(new Vec3d(player.getLocation()));
        armorStand.setNoBasePlate(true);
        // Передаём актуальный список наблюдателей
        armorStand.tick(Set.of(player));
    }

    private void spawn(Location location, Plugin plugin) {
        PlayerTracker tracker = new PlayerTracker(location.getWorld(), new Vec3d(location));

        VirtualArmorStand armorStand = VirtualEntityApi.getFactory().create(VirtualEntityType.ARMOR_STAND, VirtualArmorStand.class);
        armorStand.setCustomName(Component.text("Custom name"));
        armorStand.setCustomNameVisible(true);
        armorStand.setPos(new Vec3d(location));

        tracker.addEntity(armorStand);

        BukkitTask task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, tracker::tick, 1, 1);
        //
    }

    public static Command<CommandSender> createCommand(Plugin plugin) {
        return new Command<CommandSender>("virtualentityapi")
                .requires(new RequiresPermission<>("virtualentityapi.admin"))
                .aliases("vea")
                .aliases("ea")
                .aliases("entityapi")
                .addSubCommand(new Command<CommandSender>("spawn")
                        .requires(sender -> sender instanceof Player)
                        .argument(new ArgumentEnumValue<>("type", VirtualEntityType.class, v -> Version.VERSION.newerThanOrEqual(v.availableSinceVersion())))
                        .executor(((sender, args) -> {
                            Player player = (Player) sender;
                            VirtualEntityType type = (VirtualEntityType) args.getOrThrow("type", "Use: /vea spawn <type>");
                            VirtualEntity virtualEntity = VirtualEntityApi.getFactory().create(type);
                            virtualEntity.setPos(new Vec3d(player.getLocation()));
                            virtualEntity.tick(Set.of(player));
                        }))
                )
                .addSubCommand(new Command<CommandSender>("packetLogs")
                        .executor(((sender, args) -> {
                            Packet.debug = !Packet.debug;
                        }))
                )
                .addSubCommand(new Command<CommandSender>("test")
                        .requires(sender -> sender instanceof Player)
                        .executor(((sender, args) -> {
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

                            VirtualAreaEffectCloud areaEffectCloud = VirtualEntityApi.getFactory().create(VirtualEntityType.AREA_EFFECT_CLOUD, VirtualAreaEffectCloud.class);
                            areaEffectCloud.setPos(center);
                            areaEffectCloud.setRadius(1);
                            areaEffectCloud.setParticle(new ParticleOptions<>(null, Particle.FLAME));
                            tracker.addEntity(areaEffectCloud);


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
                                    if (tick % 3 == 0 && areaEffectCloud.getRadius() < 10) {
                                        areaEffectCloud.setRadius(areaEffectCloud.getRadius() + 0.25f);
                                    }
                                    tick++;
                                }
                            }.runTaskTimerAsynchronously(plugin, 0, 1);
                        }))
                )
                .addSubCommand(new Command<CommandSender>("spawnAll")
                        .requires(sender -> sender instanceof Player)
                        .executor(((sender, args) -> {
                            Player player = (Player) sender;
                            PlayerTracker tracker = new PlayerTracker(player.getWorld(), new Vec3d(player.getLocation()));
                            Vec3d pos = new Vec3d(player.getLocation());
                            for (VirtualEntityType value : VirtualEntityType.values()) {
                                if (Version.VERSION.olderThan(value.availableSinceVersion())) continue;
                                try {
                                    VirtualEntity entity = VirtualEntityApi.getFactory().create(value);
                                    entity.setCustomNameVisible(true);
                                    entity.setCustomName(Component.text(value.name()));
                                    entity.setNoGravity(true);
                                    entity.setPos(pos);
                                    tracker.addEntity(entity);
                                    pos = pos.add(0, 0, 2);
                                } catch (Throwable t) {
                                    System.out.println(value);
                                    t.printStackTrace();
                                }
                            }
                            tracker.tick();
                            Bukkit.getServer().getScheduler().runTaskLater(plugin, tracker::removeAll, 2000);
                        }))
                );
    }

}
