package dev.by1337.virtualentity.api.virtual.player;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.VirtualLivingEntity;

@SinceMinecraftVersion("1.21.9")
public interface VirtualAvatar extends VirtualLivingEntity {

    byte getPlayerModeCustomisation();

    void setPlayerModeCustomisation(byte customisation);

    byte getPlayerMainHand();

    void setPlayerMainHand(byte mainHand);
}
