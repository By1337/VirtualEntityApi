package dev.by1337.virtualentity.api.virtual;


import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.Pose;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;


public interface VirtualEntity extends VirtualEntityController {
    boolean isSilent();

    void setSilent(boolean flag);

    boolean isNoGravity();

    void setNoGravity(boolean flag);

    int getAirSupply();

    void setAirSupply(int airSupply);

    void setCustomName(@Nullable Component customName);

    @Nullable
    Component getCustomName();

    boolean hasCustomName();

    void setCustomNameVisible(boolean flag);

    boolean isCustomNameVisible();

    void setGlowing(boolean b);

    boolean isGlowing();

    void setShiftKeyDown(boolean flag);

    boolean isShiftKeyDown();

    boolean isCrouching();

    boolean isSprinting();

    void setSprinting(boolean flag);

    boolean isSwimming();

    void setSwimming(boolean flag);

    void setInvisible(boolean flag);

    boolean isInvisible();

    Pose getPose();

    void setPose(Pose pose);

    @SinceMinecraftVersion("1.17.1")
    int getTicksFrozen();

    @SinceMinecraftVersion("1.17.1")
    void setTicksFrozen(int ticks);
}
