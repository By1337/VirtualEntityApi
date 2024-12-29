package dev.by1337.virtualentity.api.virtual.display;

import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;

@SinceMinecraftVersion("1.19.4")
public interface VirtualTextDisplay extends VirtualDisplay {
    Component getText();

    void setText(Component component);

    int getLineWidth();

    void setLineWidth(int width);

    byte getTextOpacity();

    void setTextOpacity(byte textOpacity);

    Color getBackgroundColor();

    void setBackgroundColor(Color color, int alpha);

    byte getFlags();

    void setFlags(byte flag);

    boolean isFlagSet(byte mask);

    void setFlag(byte mask, boolean value);

    boolean isShadow();

    void setShadow(boolean shadow);

    boolean isSeeThrough();

    void setSeeThrough(boolean seeThrough);

    boolean isDefaultBackground();

    void setDefaultBackground(boolean defaultBackground);

    boolean isAlignLeft();

    void setAlignLeft(boolean alignLeft);

    boolean isAlignRight();

    void setAlignRight(boolean alignRight);

    static VirtualTextDisplay create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.TEXT_DISPLAY, VirtualTextDisplay.class);
    }
}
