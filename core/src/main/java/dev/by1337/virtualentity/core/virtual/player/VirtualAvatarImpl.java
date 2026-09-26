package dev.by1337.virtualentity.core.virtual.player;

import dev.by1337.virtualentity.api.entity.HumanoidArm;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.player.VirtualAvatar;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualLivingEntityImpl;

public class VirtualAvatarImpl extends VirtualLivingEntityImpl implements VirtualAvatar {
    private static final EntityDataAccessor<HumanoidArm> DATA_PLAYER_MAIN_HAND;
    private static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION;

    public VirtualAvatarImpl(VirtualEntityType type) {
        super(type);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PLAYER_MODE_CUSTOMISATION, (byte) 0);
        this.entityData.define(DATA_PLAYER_MAIN_HAND, HumanoidArm.RIGHT);
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
    public HumanoidArm getPlayerMainHand() {

        return this.entityData.get(DATA_PLAYER_MAIN_HAND);
    }

    /**
     * Устанавливает основную руку для игрока.
     *
     * @param mainHand байт, представляющий основную руку игрока (1 — правая, 0 — левая).
     */
    @Override
    public void setPlayerMainHand(HumanoidArm mainHand) {
        this.entityData.set(DATA_PLAYER_MAIN_HAND, mainHand);
    }

    static {
        DATA_PLAYER_MAIN_HAND = Mappings.findAccessor("Avatar", "DATA_PLAYER_MAIN_HAND");
        DATA_PLAYER_MODE_CUSTOMISATION = Mappings.findAccessor("Avatar", "DATA_PLAYER_MODE_CUSTOMISATION");
    }
}
