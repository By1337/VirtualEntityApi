package dev.by1337.virtualentity.core.virtual.player;

import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.impl.PlayerInfoPacket;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;

public class VirtualPlayerImpl extends VirtualAvatarImpl implements dev.by1337.virtualentity.api.virtual.player.VirtualPlayer {
    private static final EntityDataAccessor<Float> DATA_PLAYER_ABSORPTION_ID;
    private static final EntityDataAccessor<Integer> DATA_SCORE_ID;
    private static final Supplier<PropertyMap> PROPERTY_SUPPLIER;

    private String name = "VirtualPlayer";
    private final PropertyMap properties = PROPERTY_SUPPLIER.get();
    private final ChangingValue<GameMode> gameMode = new ChangingValue<>(GameMode.CREATIVE);
    private int latency = 0;
    private final ChangingValue<@Nullable Component> displayName = new ChangingValue<>(null);

    private final ChangingValue<Integer> listOrder = new ChangingValue<>(0);
    private final PlayerInfoPacket addPlayerPacket;
    private final PlayerInfoPacket removePlayerPacket;
    private final PlayerInfoPacket updateDisplayName;
    private final PlayerInfoPacket updateGameMode;
    private final PlayerInfoPacket updateListOrder;
    private boolean listed;

    public VirtualPlayerImpl() {
        super(VirtualEntityType.PLAYER);
        removePlayerPacket = new PlayerInfoPacket(this, PlayerInfoPacket.Action.REMOVE_PLAYER);
        addPlayerPacket = new PlayerInfoPacket(this, PlayerInfoPacket.Action.ADD_PLAYER);
        updateDisplayName = new PlayerInfoPacket(this, PlayerInfoPacket.Action.UPDATE_DISPLAY_NAME);
        updateGameMode = new PlayerInfoPacket(this, PlayerInfoPacket.Action.UPDATE_GAME_MODE);
        updateListOrder = new PlayerInfoPacket(this, PlayerInfoPacket.Action.UPDATE_LIST_ORDER);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PLAYER_ABSORPTION_ID, 0.0F);
        this.entityData.define(DATA_SCORE_ID, 0);

    }

    @Override
    public void tick(Set<Player> viewers) {
        super.tick(viewers);
        if (displayName.isChanged()) {
            broadcast(updateDisplayName);
            displayName.resetChanged();
        }
        if (gameMode.isChanged()) {
            broadcast(updateGameMode);
            gameMode.resetChanged();
        }
        if (listOrder.isChanged()) {
            broadcast(updateListOrder);
            listOrder.resetChanged();
        }
    }

    @Override
    public int getListOrder() {
        return listOrder.getVal();
    }

    @Override
    public void setListOrder(int val) {
        listOrder.setVal(val);
    }

    @Override
    public boolean isListed() {
        return listed;
    }

    @Override
    public void setListed(boolean listed) {
        this.listed = listed;
    }

    @Override
    public void removeTexture() {
        properties.removeAll("textures");
    }

    @Override
    public void setTexture(String value, String signature) {
        if (value == null || signature == null) {
            removeTexture();
        } else {
            properties.put("textures", new Property("textures", value, signature));
        }
    }

    @Override
    public void sendAddPlayerPacket(Player player) {
        addPlayerPacket.send(player);
    }

    @Override
    public void sendRemovePlayerPacket(Player player) {
        removePlayerPacket.send(player);
    }

    @Override
    protected void postSpawn(Player player) {
        super.postSpawn(player);
    }

    @Override
    protected void preSpawn(Player player) {
        super.preSpawn(player);
        addPlayerPacket.send(player);
    }

    @Override
    protected void postRemove(Player player) {
        super.postRemove(player);
        removePlayerPacket.send(player);
    }

    @Override
    public @Nullable Component getDisplayName() {
        return displayName.getVal();
    }

    @Override
    public void setDisplayName(@Nullable Component displayName) {
        this.displayName.setVal(displayName);
    }

    @Override
    public int getLatency() {
        return latency;
    }

    @Override
    public void setLatency(int latency) {
        this.latency = latency;
    }

    @Override
    public GameMode getGameMode() {
        return gameMode.getVal();
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode.setVal(gameMode);
    }

    @Override
    public PropertyMap getProperties() {
        return properties;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает текущее значение абсорбции здоровья игрока.
     *
     * @return текущее значение абсорбции здоровья.
     */
    @Override
    public float getPlayerAbsorption() {
        return this.entityData.get(DATA_PLAYER_ABSORPTION_ID);
    }

    /**
     * Устанавливает новое значение абсорбции здоровья игрока.
     *
     * @param absorption новое значение абсорбции здоровья.
     */
    @Override
    public void setPlayerAbsorption(float absorption) {
        this.entityData.set(DATA_PLAYER_ABSORPTION_ID, absorption);
    }

    /**
     * Получает текущий счёт игрока.
     *
     * @return текущий счёт игрока.
     */
    @Override
    public int getScore() {
        return this.entityData.get(DATA_SCORE_ID);
    }

    /**
     * Устанавливает новый счёт для игрока.
     *
     * @param score новый счёт игрока.
     */
    @Override
    public void setScore(int score) {
        this.entityData.set(DATA_SCORE_ID, score);
    }

    /**
     * Получает данные о левом плече игрока.
     *
     * @return данные о левом плече в виде {@link CompoundTag}.
     */
    @Override
    public CompoundTag getShoulderLeft() {
        return new CompoundTag();
    }

    /**
     * Устанавливает данные о левом плече для игрока.
     *
     * @param tag данные о левом плече в виде {@link CompoundTag}.
     */
    @Override
    public void setShoulderLeft(CompoundTag tag) {
        // Shoulder metadata is no longer part of the player protocol.
    }

    /**
     * Получает данные о правом плече игрока.
     *
     * @return данные о правом плече в виде {@link CompoundTag}.
     */
    @Override
    public CompoundTag getShoulderRight() {
        return new CompoundTag();
    }

    /**
     * Устанавливает данные о правом плече для игрока.
     *
     * @param tag данные о правом плече в виде {@link CompoundTag}.
     */
    @Override
    public void setShoulderRight(CompoundTag tag) {
        // Shoulder metadata is no longer part of the player protocol.
    }

    static {
        DATA_PLAYER_ABSORPTION_ID = Mappings.findAccessor("Player", "DATA_PLAYER_ABSORPTION_ID");
        DATA_SCORE_ID = Mappings.findAccessor("Player", "DATA_SCORE_ID");

        PROPERTY_SUPPLIER = makePropertyMapFactory();
    }

    private static Supplier<PropertyMap> makePropertyMapFactory() {
        // Paper's mutable map remains necessary on modern Authlib with immutable PropertyMap.
        try {
            var constructor = Class.forName("io.papermc.paper.profile.MutablePropertyMap").getConstructor();
            return () -> {
                try {
                    return (PropertyMap) constructor.newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new IllegalStateException("Unable to create Paper profile properties", e);
                }
            };
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static class ChangingValue<T> {
        private T val;
        private boolean changed;

        public ChangingValue(T val) {
            this.val = val;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
            changed = true;
        }

        public boolean isChanged() {
            return changed;
        }

        public void resetChanged() {
            changed = false;
        }
    }
}
