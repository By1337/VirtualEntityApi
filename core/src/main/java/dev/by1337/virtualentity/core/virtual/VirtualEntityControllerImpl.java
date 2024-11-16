package dev.by1337.virtualentity.core.virtual;

import dev.by1337.virtualentity.api.entity.EntityAnimation;
import dev.by1337.virtualentity.api.entity.EquipmentSlot;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import dev.by1337.virtualentity.api.virtual.VirtualEntityController;
import dev.by1337.virtualentity.api.virtual.VirtualLivingEntity;
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

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public abstract class VirtualEntityControllerImpl implements VirtualEntityController {
    @SuppressWarnings("deprecation")
    protected final int id = Bukkit.getUnsafe().nextEntityId();
    private final EntityPosition position = new EntityPosition();
    private final UUID uuid = UUID.randomUUID();
    private boolean onGround = true;
    private final Map<EquipmentSlot, ItemStack> equipment = new EnumMap<>(EquipmentSlot.class);
    private volatile boolean hasEquipmentChanges = false;

    protected final SynchedEntityData entityData;
    private final VirtualEntityType type;
    private final Set<Player> lastViewers = new ConcurrentPlayerHashSet();
    private final Packet removePacket;
    private Packet allEntityData;
    private Packet equipmentPacket;
    private Packet spawnPacket;
    private final PacketType spawnPacketType;
    private final VirtualEntity virtualEntity;

    public VirtualEntityControllerImpl(VirtualEntityType type) {
        virtualEntity = (VirtualEntity) this;
        this.type = type;
        entityData = new SynchedEntityData(this::onUpdate);
        defineSynchedData();
        removePacket = new RemoveEntitiesPacket(id);
        spawnPacketType = Mappings.getSpawnPacket(type);
        rebuildSpawnPacket();
    }

    protected void rebuildSpawnPacket(){
        spawnPacket = createSpawnPacket();
    }

    private Packet createSpawnPacket() {
        return switch (spawnPacketType) {
            case ADD_MOB_PACKET -> new AddMobPacket(virtualEntity);
            case ADD_ENTITY_PACKET -> new AddEntityPacket(virtualEntity);
            case ADD_PLAYER_PACKET -> new AddPlayerPacket(virtualEntity);
            case ADD_EXPERIENCE_ORB_PACKET -> new AddExperienceOrbPacket(virtualEntity);
            //case ADD_PAINTING_PACKET -> // todo
            default -> throw new IllegalStateException("Unknown spawn packet type: " + spawnPacket);
        };
    }

    @Override
    public void tick(Set<Player> viewers) {
        if (viewers.isEmpty()) {
            broadcast(removePacket);
            lastViewers.clear();
            return;
        }
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
        if (hasEquipmentChanges) {
            hasEquipmentChanges = false;
            equipmentPacket = new SetEquipmentPacket(id, equipment);
        }
        for (Player player : viewers) {
            if (lastViewers.contains(player)) {
                if (dirtyData != null) {
                    dirtyData.send(player);
                }
                if (hasEquipmentChanges && equipmentPacket != null) {
                    equipmentPacket.send(player);
                }
            } else {
                spawnPacket.send(player);
                allEntityData.send(player);
                if (equipmentPacket != null && !equipment.isEmpty()) {
                    equipmentPacket.send(player);
                }
            }
            lastViewers.remove(player);
        }
        lastViewers.forEach(removePacket::send);
        lastViewers.clear();
        lastViewers.addAll(viewers);
    }

    private void updateLocation() {
        if (!position.needPosUpdate() && !position.needRotUpdate()) return;

        Vec3d deltaPos = position.deltaPos();
        boolean shouldUseEntityTeleport = Math.abs(deltaPos.x) > 8 ||
                Math.abs(deltaPos.y) > 8 ||
                Math.abs(deltaPos.z) > 8;

        if (shouldUseEntityTeleport) {
            position.sync();
            Packet packet = new TeleportEntityPacket(virtualEntity);
            broadcast(packet);
        } else if (position.needPosUpdate() && position.needRotUpdate()) {
            Packet packet = new MoveEntityPacket.PosRot(virtualEntity);
            broadcast(packet);
            if (this instanceof VirtualLivingEntity){
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
            if (this instanceof VirtualLivingEntity){
                broadcast(new RotateHeadPacket(id, yaw())); // не для всех ентити работает, но будем пытаться на всех
            }
            position.sync();
        }
        rebuildSpawnPacket();
    }

    protected void broadcast(Packet packet) {
        lastViewers.forEach(packet::send);
    }

    protected abstract void defineSynchedData();

    protected void onUpdate(EntityDataAccessor<?> accessor) {

    }

    protected void onNewViewer(Player player) {

    }

    protected void onRemoveViewer(Player player) {

    }

    public void playAnimation(EntityAnimation animation) {
        broadcast(new AnimatePacket(id, animation));
    }

    public void lookAt(Vec3d at) {
        Vec3d delta = at.sub(position.getPos());
        double horizontalDistance = Math.sqrt(delta.x * delta.x + delta.z * delta.z);
        float pitch = (float) Math.toDegrees(Math.atan2(delta.y, horizontalDistance));
        float yaw = (float) Math.toDegrees(Math.atan2(delta.z, delta.x)) - 90.0f;
        position.setPitch(pitch);
        position.setYaw(yaw);
    }


    @Override
    public void clearEquipment() {
        equipment.clear();
        hasEquipmentChanges = true;
    }

    @Override
    public void setEquipment(EquipmentSlot slot, ItemStack item) {
        equipment.put(slot, item);
        hasEquipmentChanges = true;
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
}
