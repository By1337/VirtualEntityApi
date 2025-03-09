package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.api.entity.EntityEvent;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.task.TickTask;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.VirtualEntityController;
import dev.by1337.virtualentity.api.virtual.VirtualLivingEntity;
import dev.by1337.virtualentity.api.virtual.decoration.VirtualPainting;
import dev.by1337.virtualentity.core.controller.EquipmentController;
import dev.by1337.virtualentity.core.entity.EntityPosition;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import dev.by1337.virtualentity.core.network.impl.*;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.syncher.SynchedEntityData;
import dev.by1337.virtualentity.core.util.ConcurrentPlayerHashSet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.by1337.blib.geom.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;


public abstract class VirtualEntityControllerImpl implements VirtualEntityController {
    @SuppressWarnings("deprecation")
    protected final int id = Bukkit.getUnsafe().nextEntityId();
    private final EntityPosition position = new EntityPosition();
    private final UUID uuid = UUID.randomUUID();
    private boolean onGround = true;
    private final EquipmentController equipment = new EquipmentController();

    protected final SynchedEntityData entityData;
    private final VirtualEntityType type;
    protected final Set<Player> lastViewers = new ConcurrentPlayerHashSet();
    private final Packet removePacket;
    private Packet allEntityData;
    private Packet spawnPacket;
    private SetEntityMotionPacket motionPacket;
    private final PacketType spawnPacketType;
    private final VirtualEntity virtualEntity;
    private final List<TickTask> tickTasks = new CopyOnWriteArrayList<>();

    public VirtualEntityControllerImpl(VirtualEntityType type) {
        virtualEntity = (VirtualEntity) this;
        this.type = type;
        entityData = new SynchedEntityData(this::onUpdate);
        defineSynchedData();
        removePacket = new RemoveEntitiesPacket(id);
        spawnPacketType = Mappings.getSpawnPacket(type);
        rebuildSpawnPacket();
    }

    protected void rebuildSpawnPacket() {
        spawnPacket = createSpawnPacket();
    }

    private Packet createSpawnPacket() {
        return switch (spawnPacketType) {
            case ADD_MOB_PACKET -> new AddMobPacket(virtualEntity);
            case ADD_ENTITY_PACKET -> new AddEntityPacket(virtualEntity);
            case ADD_PLAYER_PACKET -> new AddPlayerPacket(virtualEntity);
            case ADD_EXPERIENCE_ORB_PACKET -> new AddExperienceOrbPacket(virtualEntity);
            case ADD_PAINTING_PACKET -> new AddPaintingPacket((VirtualPainting) this);
            default -> throw new IllegalStateException("Unknown spawn packet type: " + spawnPacket);
        };
    }

    @Override
    public void tick(Set<Player> viewers) {
        tickTasks.forEach(Runnable::run);
        if (viewers.isEmpty()) {
            broadcast(removePacket, this::preRemove, this::postRemove);
            lastViewers.clear();
            return;
        }
        onTick();
        updateLocation();
        Packet dirtyData;
        if (allEntityData == null || entityData.isDirty()) {
            allEntityData = new SetEntityDataPacket(id, entityData.packAll());
        }
        if (entityData.isDirty()) {
            dirtyData = new SetEntityDataPacket(id, entityData.packDirty());
        } else {
            dirtyData = null;
        }
        final SetEquipmentPacket changedSlots;
        if (equipment.isDirty()) {
            changedSlots = new SetEquipmentPacket(id, equipment.packDirty());
        } else {
            changedSlots = null;
        }
        for (Player player : viewers) {
            if (lastViewers.contains(player)) {
                if (dirtyData != null) {
                    dirtyData.send(player);
                }
                if (changedSlots != null) {
                    changedSlots.send(player);
                }
            } else {
                preSpawn(player);
                spawnPacket.send(player);
                allEntityData.send(player);
                if (!equipment.isEmpty()) {
                    new SetEquipmentPacket(id, equipment.packAll()).send(player);
                }
                if (motionPacket != null) motionPacket.send(player);
                postSpawn(player);
            }
            lastViewers.remove(player);
        }
        broadcast(removePacket, this::preRemove, this::postRemove);
        lastViewers.clear();
        lastViewers.addAll(viewers);
    }

    @Override
    public void respawn() {
        if (lastViewers.isEmpty()) return;
        broadcast(removePacket, this::preRemove, this::postRemove);
        broadcast(spawnPacket, this::preSpawn, this::postSpawn);
        broadcast(allEntityData);
        if (motionPacket != null) broadcast(motionPacket);
        if (!equipment.isEmpty()) {
            broadcast(new SetEquipmentPacket(id, equipment.packAll()));
        }
    }

    private void updateLocation() {
        if (!position.needPosUpdate() && !position.needRotUpdate()) return;

        Vec3d deltaPos = position.deltaPos();
        Vec3d abs = deltaPos.abs();
        boolean shouldUseEntityTeleport = abs.x > 8 || abs.y > 8 || abs.z > 8;

        if (shouldUseEntityTeleport) {
            position.sync();
            Packet packet = new TeleportEntityPacket(virtualEntity);
            broadcast(packet);
        } else if (position.needPosUpdate() && (position.needRotUpdate())) {
            Packet packet = new MoveEntityPacket.PosRot(virtualEntity);
            broadcast(packet);
            if (this instanceof VirtualLivingEntity) {
                broadcast(new RotateHeadPacket(id, yaw())); // не для всех ентити работает, но будем пытаться на всех
            }
            position.sync();
        } else if (position.needPosUpdate()) {
            Packet packet = new MoveEntityPacket.Pos(virtualEntity);
            broadcast(packet);
            position.sync();
        } else {
            Packet packet = new MoveEntityPacket.Rot(virtualEntity);
            broadcast(packet);
            if (this instanceof VirtualLivingEntity) {
                broadcast(new RotateHeadPacket(id, yaw())); // не для всех ентити работает, но будем пытаться на всех
            }
            position.sync();
        }
        rebuildSpawnPacket();
    }

    @Override
    public void sendEntityEvent(EntityEvent event) {
        broadcast(new EntityEventPacket(id, event));
    }

    protected void broadcast(Packet packet, Consumer<Player> pre, Consumer<Player> post) {
        lastViewers.forEach(p -> {
            pre.accept(p);
            packet.send(p);
            post.accept(p);
        });
    }

    protected void broadcast(Packet packet) {
        lastViewers.forEach(packet::send);
    }

    protected abstract void defineSynchedData();

    protected void onUpdate(EntityDataAccessor<?> accessor) {

    }

    protected void postSpawn(Player player) {
    }

    protected void preSpawn(Player player) {
    }

    protected void postRemove(Player player) {
    }

    protected void preRemove(Player player) {

    }

    public void playAnimation(EntityAnimation animation) {
        broadcast(new AnimatePacket(id, animation));
    }

    public void lookAt(Vec3d at) {
        Vec3d delta = at.sub(position.getPos());
        double horizontalDistance = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
        float pitch = wrapDegrees((float) -(Math.atan2(delta.y, horizontalDistance) * 57.2957763671875)); // Convert radians to degrees (180 / PI)
        float yaw = wrapDegrees((float) (Math.atan2(delta.z, delta.x) * 57.2957763671875) - 90.0f); // Convert radians to degrees (180 / PI)
        position.setPitch(pitch);
        position.setYaw(yaw);
    }

    private static float wrapDegrees(float f) {
        float f1 = f % 360.0F;
        if (f1 >= 180.0F) {
            f1 -= 360.0F;
        }
        if (f1 < -180.0F) {
            f1 += 360.0F;
        }
        return f1;
    }

    @Override
    public void clearEquipment() {
        equipment.clear();
    }

    @Override
    public void setEquipment(EquipmentSlot slot, @Nullable ItemStack item) {
        equipment.set(slot, item);
    }

    @Nullable
    @Override
    public ItemStack getEquipment(EquipmentSlot slot) {
        return equipment.get(slot);
    }

    @Override
    public void setPos(Vec3d pos) {
        position.setPos(pos);
    }

    @Override
    public Vec3d getPos() {
        return position.getPos();
    }

    @Override
    public Vec3d getOldPos() {
        return position.getPosOld();
    }

    @Override
    public byte yaw() {
        return position.yaw();
    }

    @Override
    public byte pitch() {
        return position.pitch();
    }

    @Override
    public byte dimensions() {
        return position.dimensions();
    }

    @Override
    public void setYaw(float yaw) {
        position.setYaw(yaw);
    }

    @Override
    public void setPitch(float pitch) {
        position.setPitch(pitch);
    }

    @Override
    public void setDimensions(float dimensions) {
        position.setDimensions(dimensions);
    }

    @Override
    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public VirtualEntityType getType() {
        return type;
    }

    @Override
    public int getCustomEntityData() {
        return 0;
    }

    @Override
    public float getYaw() {
        return position.getYaw();
    }

    @Override
    public float getPitch() {
        return position.getPitch();
    }

    @Override
    public float getDimensions() {
        return position.getDimensions();
    }

    @Override
    public int getId() {
        return id;
    }

    public SynchedEntityData entityData() {
        return entityData;
    }

    public Set<Player> getLastViewers() {
        return lastViewers;
    }

    @Override
    public void setMotion(Vec3d motion) {
        motionPacket = new SetEntityMotionPacket(getId(), motion);
        broadcast(motionPacket);
    }

    @Override
    public void addTickTask(TickTask task) {
        tickTasks.add(task);
    }

    @Override
    public boolean removeTickTask(TickTask task) {
        return tickTasks.remove(task);
    }

    @Override
    public void removeAllTickTask() {
        tickTasks.clear();
    }
}
