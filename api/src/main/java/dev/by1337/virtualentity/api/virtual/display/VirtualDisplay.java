package dev.by1337.virtualentity.api.virtual.display;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.BillboardConstraints;
import dev.by1337.virtualentity.api.util.Transformation;
import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.Color;
import org.jetbrains.annotations.Nullable;

@SinceMinecraftVersion("1.19.4")
public interface VirtualDisplay extends VirtualEntity {
    void setTransformation(Transformation transformation);

    void setInterpolationDuration(int i);

    int getInterpolationDuration();

    void setInterpolationDelay(int i);

    int getInterpolationDelay();

    void setBillboardConstraints(BillboardConstraints billboardConstraints);

    void setBrightnessOverride(int block, int sky);

    int getPackedBrightnessOverride();

    void setViewRange(float f);

    float getViewRange();

    void setShadowRadius(float f);

    float getShadowRadius();

    void setShadowStrength(float f);

    float getShadowStrength();

    void setWidth(float f);

    float getWidth();

    void setHeight(float f);

    @Nullable Color getGlowColorOverride();

    void setGlowColorOverride(@Nullable Color color, int alpha);

    float getHeight();
}
