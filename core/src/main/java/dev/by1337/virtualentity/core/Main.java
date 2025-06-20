package dev.by1337.virtualentity.core;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.particles.ParticleOptions;
import dev.by1337.virtualentity.api.tracker.PlayerTracker;
import dev.by1337.virtualentity.api.virtual.VirtualAreaEffectCloud;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.decoration.VirtualArmorStand;
import dev.by1337.virtualentity.api.virtual.decoration.VirtualItemFrame;
import dev.by1337.virtualentity.api.virtual.item.VirtualItem;
import dev.by1337.virtualentity.api.virtual.monster.VirtualCreeper;
import dev.by1337.virtualentity.api.virtual.player.VirtualPlayer;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.mappings.VirtualEntityRegistrar;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.util.MappingsDiffGenerator;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.by1337.blib.command.Command;
import org.by1337.blib.command.CommandWrapper;
import org.by1337.blib.command.argument.ArgumentEnumValue;
import org.by1337.blib.command.requires.RequiresPermission;
import org.by1337.blib.geom.Vec3d;
import org.by1337.blib.nbt.MojangNbtReader;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.by1337.blib.util.Direction;
import org.by1337.blib.util.Version;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
    }

    public static Command<CommandSender> createCommand(Plugin plugin) {
        return new Command<CommandSender>("virtualentityapi")
                .requires(new RequiresPermission<>("virtualentityapi.admin"))
                .aliases("vea")
                .aliases("ea")
                .aliases("entityapi")
                .addSubCommand(new Command<CommandSender>("spawn")
                        .requires(sender -> sender instanceof Player)
                        .argument(new ArgumentEnumValue<>("type", VirtualEntityType.class,
                                        v -> Version.VERSION.newerThanOrEqual(v.availableSinceVersion()) &&
                                                (v.removedIn() == null || Version.VERSION.olderThan(v.removedIn()))
                                )
                        )
                        .executor(((sender, args) -> {
                            Player player = (Player) sender;
                            VirtualEntityType type = (VirtualEntityType) args.getOrThrow("type", "Use: /vea spawn <type>");
                            VirtualEntity virtualEntity = VirtualEntityApi.getFactory().create(type);
                            virtualEntity.setPos(new Vec3d(player.getLocation()));
                            virtualEntity.tick(Set.of(player));


                            if (virtualEntity instanceof VirtualPlayer vp) {
                                Bukkit.getScheduler().runTaskLater(plugin, () -> vp.sendRemovePlayerPacket(player), 60);
                            }
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

                            VirtualArmorStand armorStand = VirtualArmorStand.create();
                            final Vec3d center = new Vec3d(player.getLocation());
                            final Vec3d spawnPos = center.add(0, 0, 0);
                            armorStand.setPos(spawnPos);
                            armorStand.setEquipment(EquipmentSlot.HEAD, new ItemStack(Material.RED_SHULKER_BOX));
                            tracker.addEntity(armorStand);

                            VirtualCreeper creeper = VirtualCreeper.create();
                            creeper.setPos(new Vec3d(player.getLocation()));
                            creeper.lookAt(armorStand.getPos());
                            creeper.setPowered(true);
                            tracker.addEntity(creeper);

                            VirtualAreaEffectCloud areaEffectCloud = VirtualAreaEffectCloud.create();
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
                                        VirtualItem item = VirtualItem.create();
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
                                if (value.removedIn() != null && Version.VERSION.newerThanOrEqual(value.removedIn()))
                                    continue;
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
                )
                .addSubCommand(new Command<CommandSender>("frameTest")
                        .requires(sender -> sender instanceof Player)
                        .executor(((sender, args) -> {
                            Player player = (Player) sender;
                            VirtualItemFrame frame = VirtualItemFrame.create();
                            frame.setPos(new Vec3d(player.getLocation()));
                            frame.setItem(new ItemStack(Material.RED_SHULKER_BOX));
                            frame.tick(Set.of(player));
                            new BukkitRunnable() {
                                Direction[] arr = Direction.values();
                                int pos = 0;

                                @Override
                                public void run() {
                                    if (pos == arr.length) {
                                        frame.tick(Set.of());
                                        cancel();
                                        return;
                                    }
                                    frame.setDirection(arr[pos++]);
                                    frame.tick(Set.of(player));
                                    if (Version.VERSION.newerThanOrEqual(Version.V1_21_3)) {
                                        frame.respawn();
                                    }
                                }
                            }.runTaskTimerAsynchronously(plugin, 0, 15);
                        }))
                )
                .addSubCommand(new Command<CommandSender>("equipmentTest")
                        .requires(sender -> sender instanceof Player)
                        .executor(((sender, args) -> {
                            Player player = (Player) sender;
                            VirtualArmorStand stand = VirtualArmorStand.create();
                            stand.setPos(new Vec3d(player.getLocation()));
                            stand.tick(Set.of(player));

                            new BukkitRunnable() {
                                EquipmentSlot slot = EquipmentSlot.MAINHAND;

                                @Override
                                public void run() {
                                    stand.clearEquipment();
                                    switch (slot) {
                                        case MAINHAND -> {
                                            stand.setEquipment(slot, new ItemStack(Material.DIAMOND_SWORD));
                                            slot = EquipmentSlot.OFFHAND;
                                        }
                                        case OFFHAND -> {
                                            stand.setEquipment(slot, new ItemStack(Material.NETHERITE_SWORD));
                                            slot = EquipmentSlot.FEET;
                                        }
                                        case FEET -> {
                                            stand.setEquipment(slot, new ItemStack(Material.DIAMOND_BOOTS));
                                            slot = EquipmentSlot.LEGS;
                                        }
                                        case LEGS -> {
                                            stand.setEquipment(slot, new ItemStack(Material.DIAMOND_LEGGINGS));
                                            slot = EquipmentSlot.CHEST;
                                        }
                                        case CHEST -> {
                                            stand.setEquipment(slot, new ItemStack(Material.DIAMOND_CHESTPLATE));
                                            slot = EquipmentSlot.HEAD;
                                        }
                                        case HEAD -> {
                                            stand.setEquipment(slot, new ItemStack(Material.DIAMOND_HELMET));
                                            slot = EquipmentSlot.BODY;
                                        }
                                        default -> {
                                            cancel();
                                            stand.tick(Set.of());
                                            return;
                                        }
                                    }
                                    stand.tick(Set.of(player));
                                }
                            }.runTaskTimerAsynchronously(plugin, 0, 20);
                        }))
                ).addSubCommand(new Command<CommandSender>("diff")
                        .argument(new ArgumentEnumValue<>("version", Version.class))
                        .executor(((sender, args) -> {
                            Version current = Version.VERSION;
                            Version version = (Version) args.getOrThrow("version", "version is not selected");
                            CompoundTag tags1;
                            CompoundTag tags2;
                            try (var in = Mappings.getMappingsInputStream(current)) {
                                tags1 = MojangNbtReader.readCompressed(in);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try (var in = Mappings.getMappingsInputStream(version)) {
                                tags2 = MojangNbtReader.readCompressed(in);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            String result = MappingsDiffGenerator.createDiff(tags1, tags2);
                            try {
                                Path out = plugin.getDataFolder().toPath().resolve(current.getVer() + "_and_" + version.getVer() + "_diff.txt");
                                plugin.getDataFolder().mkdirs();
                                Files.writeString(out, result, StandardCharsets.UTF_8);
                                sender.sendMessage("saved to " + out);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }))
                )
                ;
    }

}
