package dev.by1337.virtualentity.core.virtual.player;

import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.network.impl.PlayerInfoPacket;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualLivingEntityImpl;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class VirtualPlayerImpl extends VirtualLivingEntityImpl implements dev.by1337.virtualentity.api.virtual.player.VirtualPlayer {
    private static final EntityDataAccessor<Float> DATA_PLAYER_ABSORPTION_ID;
    private static final EntityDataAccessor<Integer> DATA_SCORE_ID;
    private static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION;
    private static final EntityDataAccessor<Byte> DATA_PLAYER_MAIN_HAND;
    private static final EntityDataAccessor<CompoundTag> DATA_SHOULDER_LEFT;
    private static final EntityDataAccessor<CompoundTag> DATA_SHOULDER_RIGHT;

    private String name = "VirtualPlayer";
    private final PropertyMap properties = new PropertyMap();
    private final ChangingValue<GameMode> gameMode = new ChangingValue<>(GameMode.CREATIVE);
    private int latency = 0;
    private final ChangingValue<@Nullable Component> displayName = new ChangingValue<>(null);
    @SinceMinecraftVersion("1.21.3")
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
        this.entityData.define(DATA_PLAYER_MODE_CUSTOMISATION, (byte) 0);
        this.entityData.define(DATA_PLAYER_MAIN_HAND, (byte) 1);
        this.entityData.define(DATA_SHOULDER_LEFT, new CompoundTag());
        this.entityData.define(DATA_SHOULDER_RIGHT, new CompoundTag());
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
    @SinceMinecraftVersion("1.21.3")
    public int getListOrder() {
        return listOrder.getVal();
    }

    @Override
    @SinceMinecraftVersion("1.21.3")
    public void setListOrder(int val) {
        listOrder.setVal(val);
    }

    @Override
    @SinceMinecraftVersion("1.19.4")
    public boolean isListed() {
        return listed;
    }

    @Override
    @SinceMinecraftVersion("1.19.4")
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
     * Получает текущий режим кастомизации игрока.
     *
     * @return режим кастомизации в виде байта.
     */
    @Override
    public byte getPlayerModeCustomisation() {
        return this.entityData.get(DATA_PLAYER_MODE_CUSTOMISATION);
    }

    /**
     * Устанавливает новый режим кастомизации для игрока.
     *
     * @param customisation новый режим кастомизации.
     */
    @Override
    public void setPlayerModeCustomisation(byte customisation) {
        this.entityData.set(DATA_PLAYER_MODE_CUSTOMISATION, customisation);
    }

    /**
     * Получает текущую основную руку игрока.
     *
     * @return основная рука игрока в виде байта (1 — правая, 0 — левая).
     */
    @Override
    public byte getPlayerMainHand() {
        return this.entityData.get(DATA_PLAYER_MAIN_HAND);
    }

    /**
     * Устанавливает основную руку для игрока.
     *
     * @param mainHand байт, представляющий основную руку игрока (1 — правая, 0 — левая).
     */
    @Override
    public void setPlayerMainHand(byte mainHand) {
        this.entityData.set(DATA_PLAYER_MAIN_HAND, mainHand);
    }

    /**
     * Получает данные о левом плече игрока.
     *
     * @return данные о левом плече в виде {@link CompoundTag}.
     */
    @Override
    public CompoundTag getShoulderLeft() {
        return this.entityData.get(DATA_SHOULDER_LEFT);
    }

    /**
     * Устанавливает данные о левом плече для игрока.
     *
     * @param tag данные о левом плече в виде {@link CompoundTag}.
     */
    @Override
    public void setShoulderLeft(CompoundTag tag) {
        this.entityData.set(DATA_SHOULDER_LEFT, tag);
    }

    /**
     * Получает данные о правом плече игрока.
     *
     * @return данные о правом плече в виде {@link CompoundTag}.
     */
    @Override
    public CompoundTag getShoulderRight() {
        return this.entityData.get(DATA_SHOULDER_RIGHT);
    }

    /**
     * Устанавливает данные о правом плече для игрока.
     *
     * @param tag данные о правом плече в виде {@link CompoundTag}.
     */
    @Override
    public void setShoulderRight(CompoundTag tag) {
        this.entityData.set(DATA_SHOULDER_RIGHT, tag);
    }


    static {
        DATA_PLAYER_ABSORPTION_ID = Mappings.findAccessor("Player", "DATA_PLAYER_ABSORPTION_ID");
        DATA_SCORE_ID = Mappings.findAccessor("Player", "DATA_SCORE_ID");
        DATA_PLAYER_MODE_CUSTOMISATION = Mappings.findAccessor("Player", "DATA_PLAYER_MODE_CUSTOMISATION");
        DATA_PLAYER_MAIN_HAND = Mappings.findAccessor("Player", "DATA_PLAYER_MAIN_HAND");
        DATA_SHOULDER_LEFT = Mappings.findAccessor("Player", "DATA_SHOULDER_LEFT");
        DATA_SHOULDER_RIGHT = Mappings.findAccessor("Player", "DATA_SHOULDER_RIGHT");
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
