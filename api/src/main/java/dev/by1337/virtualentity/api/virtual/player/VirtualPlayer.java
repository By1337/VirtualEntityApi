package dev.by1337.virtualentity.api.virtual.player;

import dev.by1337.virtualentity.api.virtual.VirtualLivingEntity;
import org.by1337.blib.nbt.impl.CompoundTag;

public interface VirtualPlayer extends VirtualLivingEntity {
    float getPlayerAbsorption();

    void setPlayerAbsorption(float absorption);

    int getScore();

    void setScore(int score);

    byte getPlayerModeCustomisation();

    void setPlayerModeCustomisation(byte customisation);

    byte getPlayerMainHand();

    void setPlayerMainHand(byte mainHand);

    CompoundTag getShoulderLeft();

    void setShoulderLeft(CompoundTag tag);

    CompoundTag getShoulderRight();

    void setShoulderRight(CompoundTag tag);
}
