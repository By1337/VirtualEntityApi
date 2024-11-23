package dev.by1337.virtualentity.core.virtual.display;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.util.ColorUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;

@SinceMinecraftVersion("1.19.4")
public class VirtualTextDisplayImpl extends VirtualDisplayImpl implements dev.by1337.virtualentity.api.virtual.display.VirtualTextDisplay {
    // todo version sensitive
    public static final byte FLAG_SHADOW = 1; // 00000001
    public static final byte FLAG_SEE_THROUGH = 2; // 00000010
    public static final byte FLAG_DEFAULT_BACKGROUND = 4; // 00000100
    public static final byte FLAG_ALIGN_LEFT = 8; // 00001000
    public static final byte FLAG_ALIGN_RIGHT = 16; // 00010000

    private static final EntityDataAccessor<Component> DATA_TEXT_ID;
    private static final EntityDataAccessor<Integer> DATA_LINE_WIDTH_ID;
    private static final EntityDataAccessor<Integer> DATA_BACKGROUND_COLOR_ID;
    private static final EntityDataAccessor<Byte> DATA_TEXT_OPACITY_ID;
    private static final EntityDataAccessor<Byte> DATA_STYLE_FLAGS_ID;

    public VirtualTextDisplayImpl() {
        super(VirtualEntityType.TEXT_DISPLAY);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TEXT_ID, Component.empty());
        this.entityData.define(DATA_LINE_WIDTH_ID, 200);
        this.entityData.define(DATA_BACKGROUND_COLOR_ID, 0x40000000);
        this.entityData.define(DATA_TEXT_OPACITY_ID, (byte) -1);
        this.entityData.define(DATA_STYLE_FLAGS_ID, (byte) 0);
    }

    @Override
    public Component getText() {
        return this.entityData.get(DATA_TEXT_ID);
    }

    @Override
    public void setText(Component component) {
        this.entityData.set(DATA_TEXT_ID, component);
    }

    @Override
    public int getLineWidth() {
        return this.entityData.get(DATA_LINE_WIDTH_ID);
    }

    @Override
    public void setLineWidth(int id) {
        this.entityData.set(VirtualTextDisplayImpl.DATA_LINE_WIDTH_ID, id);
    }


    @Override
    public byte getTextOpacity() {
        return this.entityData.get(DATA_TEXT_OPACITY_ID);
    }

    @Override
    public void setTextOpacity(byte textOpacity) {
        this.entityData.set(DATA_TEXT_OPACITY_ID, textOpacity);
    }


    @Override
    public Color getBackgroundColor() {
        return Color.fromRGB(this.entityData.get(DATA_BACKGROUND_COLOR_ID));
    }

    @Override
    public void setBackgroundColor(Color color, int alpha) {
        this.entityData.set(DATA_BACKGROUND_COLOR_ID, ColorUtil.asARGB(color, alpha));
    }

    @Override
    public byte getFlags() {
        return this.entityData.get(DATA_STYLE_FLAGS_ID);
    }

    @Override
    public void setFlags(byte flag) {
        this.entityData.set(DATA_STYLE_FLAGS_ID, flag);
    }

    @Override
    public boolean isFlagSet(byte mask) {
        return (getFlags() & mask) != 0;
    }


    @Override
    public void setFlag(byte mask, boolean value) {
        byte flags = getFlags();
        if (value) {
            flags |= mask;
        } else {
            flags &= (byte) ~mask;
        }
        setFlags(flags);
    }

    @Override
    public boolean isShadow() {
        return isFlagSet(FLAG_SHADOW);
    }

    @Override
    public void setShadow(boolean shadow) {
        setFlag(FLAG_SHADOW, shadow);
    }

    @Override
    public boolean isSeeThrough() {
        return isFlagSet(FLAG_SEE_THROUGH);
    }

    @Override
    public void setSeeThrough(boolean seeThrough) {
        setFlag(FLAG_SEE_THROUGH, seeThrough);
    }

    @Override
    public boolean isDefaultBackground() {
        return isFlagSet(FLAG_DEFAULT_BACKGROUND);
    }

    @Override
    public void setDefaultBackground(boolean defaultBackground) {
        setFlag(FLAG_DEFAULT_BACKGROUND, defaultBackground);
    }

    @Override
    public boolean isAlignLeft() {
        return isFlagSet(FLAG_ALIGN_LEFT);
    }

    @Override
    public void setAlignLeft(boolean alignLeft) {
        setFlag(FLAG_ALIGN_LEFT, alignLeft);
    }

    @Override
    public boolean isAlignRight() {
        return isFlagSet(FLAG_ALIGN_RIGHT);
    }

    @Override
    public void setAlignRight(boolean alignRight) {
        setFlag(FLAG_ALIGN_RIGHT, alignRight);
    }

    static {
        DATA_TEXT_ID = Mappings.findAccessor("TextDisplay", "DATA_TEXT_ID");
        DATA_LINE_WIDTH_ID = Mappings.findAccessor("TextDisplay", "DATA_LINE_WIDTH_ID");
        DATA_BACKGROUND_COLOR_ID = Mappings.findAccessor("TextDisplay", "DATA_BACKGROUND_COLOR_ID");
        DATA_TEXT_OPACITY_ID = Mappings.findAccessor("TextDisplay", "DATA_TEXT_OPACITY_ID");
        DATA_STYLE_FLAGS_ID = Mappings.findAccessor("TextDisplay", "DATA_STYLE_FLAGS_ID");
    }
}