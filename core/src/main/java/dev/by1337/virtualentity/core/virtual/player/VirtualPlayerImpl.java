package dev.by1337.virtualentity.core.virtual.player;

import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualLivingEntityImpl;
import org.by1337.blib.nbt.impl.CompoundTag;

public class VirtualPlayerImpl extends VirtualLivingEntityImpl implements dev.by1337.virtualentity.api.virtual.player.VirtualPlayer {
    private static final EntityDataAccessor<Float> DATA_PLAYER_ABSORPTION_ID;
    private static final EntityDataAccessor<Integer> DATA_SCORE_ID;
    private static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION;
    private static final EntityDataAccessor<Byte> DATA_PLAYER_MAIN_HAND;
    private static final EntityDataAccessor<CompoundTag> DATA_SHOULDER_LEFT;
    private static final EntityDataAccessor<CompoundTag> DATA_SHOULDER_RIGHT;

    public VirtualPlayerImpl() {
        super(VirtualEntityType.PLAYER);
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
}
