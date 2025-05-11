# VirtualEntityApi

**VirtualEntityApi** is an API for creating packet entities.

### Features:
- **Minimal use of NMS**.
- **Maximum ease of use**.

Supported versions: 1.16.5, 1.17.1, 1.18.2, 1.19.4, 1.20.1, 1.20.4, 1.20.6, 1.21, 1.21.1, 1.21.3, 1.21.4

---

## Example Usage

### Creating a packet mob
```java
public void test(Player player) {
    // Create a packet ArmorStand
    VirtualArmorStand armorStand = VirtualArmorStand.create();
    
    // Set parameters
    armorStand.setCustomName(Component.text("Custom name"));
    armorStand.setCustomNameVisible(true);
    armorStand.setPos(new Vec3d(player.getLocation()));
    armorStand.setNoBasePlate(true);
    
    // Pass the current list of observers
    armorStand.tick(Set.of(player));
}
```

Working with packet mobs is very similar to working with regular mobs, except for the following differences:
- Packet mobs **do not depend on chunk loading** (whether they are unloaded/loaded).
- They ignore game rules such as `mob-spawning deny`, burning in sunlight, and physics.
- They maintain their position even in the air and do not account for `velocity`.

---

### Automatic `tick` invocation with `PlayerTracker`
```java
private void spawn(Location location, Plugin plugin) {
    PlayerTracker tracker = new PlayerTracker(location.getWorld(), new Vec3d(location));
    
    VirtualArmorStand armorStand = VirtualArmorStand.create();
    armorStand.setCustomName(Component.text("Custom name"));
    armorStand.setCustomNameVisible(true);
    armorStand.setPos(new Vec3d(location));
    
    tracker.addEntity(armorStand);
    BukkitTask task = plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, tracker::tick, 1, 1);
}
```

`PlayerTracker` automatically searches for players in the specified world and radius, invoking `tick` for the entities.

---

## Integration

### Maven
```xml
<repositories>
    <repository>
        <id>by1337-repo</id>
        <url>https://repo.by1337.space/repository/maven-releases/</url>
    </repository>
</repositories>

<dependencies>
<dependency>
    <groupId>dev.by1337.virtualentity.api</groupId>
    <artifactId>VirtualEntityApi-api</artifactId>
    <version>1.2.4</version>
    <scope>provided</scope>
</dependency>
</dependencies>
```

### Gradle
```groovy
repositories {
    maven {
        name = "by1337-repo"
        url = "https://repo.by1337.space/repository/maven-releases/"
    }
}

dependencies {
    compileOnly 'dev.by1337.virtualentity.api:VirtualEntityApi-api:1.2.4'
}
```

---

### License
[MIT](LICENSE)