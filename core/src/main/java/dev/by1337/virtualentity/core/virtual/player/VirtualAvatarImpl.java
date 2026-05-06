package dev.by1337.virtualentity.core.virtual.player;

import dev.by1337.core.ServerVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.HumanoidArm;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.player.VirtualAvatar;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualLivingEntityImpl;

@SinceMinecraftVersion("1.21.9")
public class VirtualAvatarImpl extends VirtualLivingEntityImpl implements VirtualAvatar {
    private static final EntityDataAccessor DATA_PLAYER_MAIN_HAND;
    private static final EntityDataAccessor<Byte> DATA_PLAYER_MODE_CUSTOMISATION;

    public VirtualAvatarImpl(VirtualEntityType type) {
        super(type);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_PLAYER_MODE_CUSTOMISATION, (byte) 0);
        if (ServerVersion.is1_21_9orOlder()) {
            this.entityData.define(DATA_PLAYER_MAIN_HAND, (byte) 1);
        } else {
            this.entityData.define(DATA_PLAYER_MAIN_HAND, HumanoidArm.RIGHT);
        }
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
        if (ServerVersion.is1_21_9orOlder()) {
            var v = (byte) this.entityData.get(DATA_PLAYER_MAIN_HAND);
            return v == 0 ? HumanoidArm.LEFT : HumanoidArm.RIGHT;
        }
        return (HumanoidArm) this.entityData.get(DATA_PLAYER_MAIN_HAND);
    }

    /**
     * Устанавливает основную руку для игрока.
     *
     * @param mainHand байт, представляющий основную руку игрока (1 — правая, 0 — левая).
     */
    @Override
    public void setPlayerMainHand(HumanoidArm mainHand) {
        if (ServerVersion.is1_21_9orOlder()) {
            this.entityData.set(DATA_PLAYER_MAIN_HAND, mainHand.getId());
        } else {
            this.entityData.set(DATA_PLAYER_MAIN_HAND, mainHand);
        }
    }


    static {
        if (ServerVersion.is1_21_9orNewer()) {
            DATA_PLAYER_MAIN_HAND = Mappings.findAccessor("Avatar", "DATA_PLAYER_MAIN_HAND");
            DATA_PLAYER_MODE_CUSTOMISATION = Mappings.findAccessor("Avatar", "DATA_PLAYER_MODE_CUSTOMISATION");
        } else {
            DATA_PLAYER_MODE_CUSTOMISATION = Mappings.findAccessor("Player", "DATA_PLAYER_MODE_CUSTOMISATION");
            DATA_PLAYER_MAIN_HAND = Mappings.findAccessor("Player", "DATA_PLAYER_MAIN_HAND");
        }
    }
}