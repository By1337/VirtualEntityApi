package dev.by1337.virtualentity.core.virtual.display;

import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.BillboardConstraints;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.util.Transformation;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.syncher.SynchedEntityData;
import dev.by1337.virtualentity.core.util.ColorUtil;
import dev.by1337.virtualentity.core.virtual.VirtualEntityImpl;
import org.bukkit.Color;
import org.by1337.blib.util.Version;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@SinceMinecraftVersion("1.19.4")
public class VirtualDisplayImpl extends VirtualEntityImpl implements dev.by1337.virtualentity.api.virtual.display.VirtualDisplay {
    private static final EntityDataAccessor<Integer> DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID;
    private static final EntityDataAccessor<Integer> DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID;
    @SinceMinecraftVersion("1.20.4")
    private static final EntityDataAccessor<Integer> DATA_POS_ROT_INTERPOLATION_DURATION_ID;
    private static final EntityDataAccessor<Vector3f> DATA_TRANSLATION_ID;
    private static final EntityDataAccessor<Vector3f> DATA_SCALE_ID;
    private static final EntityDataAccessor<Quaternionf> DATA_LEFT_ROTATION_ID;
    private static final EntityDataAccessor<Quaternionf> DATA_RIGHT_ROTATION_ID;
    private static final EntityDataAccessor<Byte> DATA_BILLBOARD_RENDER_CONSTRAINTS_ID;
    private static final EntityDataAccessor<Integer> DATA_BRIGHTNESS_OVERRIDE_ID;
    private static final EntityDataAccessor<Float> DATA_VIEW_RANGE_ID;
    private static final EntityDataAccessor<Float> DATA_SHADOW_RADIUS_ID;
    private static final EntityDataAccessor<Float> DATA_SHADOW_STRENGTH_ID;
    private static final EntityDataAccessor<Float> DATA_WIDTH_ID;
    private static final EntityDataAccessor<Float> DATA_HEIGHT_ID;
    private static final EntityDataAccessor<Integer> DATA_GLOW_COLOR_OVERRIDE_ID;


    protected VirtualDisplayImpl(VirtualEntityType type) {
        super(type);
    }

    private static Transformation createTransformation(SynchedEntityData entityData) {
        Vector3f translation = entityData.get(DATA_TRANSLATION_ID);
        Quaternionf leftRotation = entityData.get(DATA_LEFT_ROTATION_ID);
        Vector3f scale = entityData.get(DATA_SCALE_ID);
        Quaternionf rightRotation = entityData.get(DATA_RIGHT_ROTATION_ID);
        return new Transformation(translation, leftRotation, scale, rightRotation);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID, 0);
        this.entityData.define(DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID, 0);
        this.entityData.define(DATA_TRANSLATION_ID, new Vector3f());
        this.entityData.define(DATA_SCALE_ID, new Vector3f(1.0F, 1.0F, 1.0F));
        this.entityData.define(DATA_RIGHT_ROTATION_ID, new Quaternionf());
        this.entityData.define(DATA_LEFT_ROTATION_ID, new Quaternionf());
        this.entityData.define(DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, (byte) BillboardConstraints.FIXED.getId());
        this.entityData.define(DATA_BRIGHTNESS_OVERRIDE_ID, -1);
        this.entityData.define(DATA_VIEW_RANGE_ID, 1.0F);
        this.entityData.define(DATA_SHADOW_RADIUS_ID, 0.0F);
        this.entityData.define(DATA_SHADOW_STRENGTH_ID, 1.0F);
        this.entityData.define(DATA_WIDTH_ID, 0.0F);
        this.entityData.define(DATA_HEIGHT_ID, 0.0F);
        this.entityData.define(DATA_GLOW_COLOR_OVERRIDE_ID, -1);
        if (Version.VERSION.newerThanOrEqual(Version.V1_20_4)) {
            entityData.define(DATA_POS_ROT_INTERPOLATION_DURATION_ID, 0);
        }
    }

    @Override
    public void setTransformation(Transformation transformation) {
        this.entityData.set(DATA_TRANSLATION_ID, transformation.getTranslation());
        this.entityData.set(DATA_LEFT_ROTATION_ID, transformation.getLeftRotation());
        this.entityData.set(DATA_SCALE_ID, transformation.getScale());
        this.entityData.set(DATA_RIGHT_ROTATION_ID, transformation.getRightRotation());
    }

    @SinceMinecraftVersion("1.20.4")
    public void setPosRotInterpolationDuration(int i) {
        this.entityData.set(DATA_POS_ROT_INTERPOLATION_DURATION_ID, i);
    }

    @SinceMinecraftVersion("1.20.4")
    public int getPosRotInterpolationDuration() {
        return this.entityData.get(DATA_POS_ROT_INTERPOLATION_DURATION_ID);
    }


    @Override
    public void setInterpolationDuration(int i) {
        setTransformationInterpolationDuration(i);
    }

    @Override
    public int getInterpolationDuration() {
        return getTransformationInterpolationDuration();
    }

    public void setTransformationInterpolationDuration(int i) {
        this.entityData.set(DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID, i);
    }

    public int getTransformationInterpolationDuration() {
        return this.entityData.get(DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID);
    }

    @Override
    public void setInterpolationDelay(int i) {
        setTransformationInterpolationDelay(i);
    }

    @Override
    public int getInterpolationDelay() {
        return getTransformationInterpolationDelay();
    }

    public void setTransformationInterpolationDelay(int i) {
        this.entityData.set(DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID, i, true);
    }

    public int getTransformationInterpolationDelay() {
        return this.entityData.get(DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID);
    }

    @Override
    public void setBillboardConstraints(BillboardConstraints billboardConstraints) {
        this.entityData.set(DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, (byte) billboardConstraints.getId());
    }

    @Override
    public void setBrightnessOverride(int block, int sky) {
        this.entityData.set(DATA_BRIGHTNESS_OVERRIDE_ID,
                (block < 0 || sky < 0) ? 0 :
                        Math.max(block, 15) << 4 | Math.max(sky, 15) << 20
        );
    }

    @Override
    public int getPackedBrightnessOverride() {
        return this.entityData.get(DATA_BRIGHTNESS_OVERRIDE_ID);
    }

    @Override
    public void setViewRange(float f) {
        this.entityData.set(DATA_VIEW_RANGE_ID, f);
    }

    @Override
    public float getViewRange() {
        return this.entityData.get(DATA_VIEW_RANGE_ID);
    }

    @Override
    public void setShadowRadius(float f) {
        this.entityData.set(DATA_SHADOW_RADIUS_ID, f);
    }

    @Override
    public float getShadowRadius() {
        return this.entityData.get(DATA_SHADOW_RADIUS_ID);
    }

    @Override
    public void setShadowStrength(float f) {
        this.entityData.set(DATA_SHADOW_STRENGTH_ID, f);
    }

    @Override
    public float getShadowStrength() {
        return this.entityData.get(DATA_SHADOW_STRENGTH_ID);
    }

    @Override
    public void setWidth(float f) {
        this.entityData.set(DATA_WIDTH_ID, f);
    }

    @Override
    public float getWidth() {
        return this.entityData.get(DATA_WIDTH_ID);
    }

    @Override
    public void setHeight(float f) {
        this.entityData.set(DATA_HEIGHT_ID, f);
    }

    @Override
    public @Nullable Color getGlowColorOverride() {
        int color = this.entityData.get(DATA_GLOW_COLOR_OVERRIDE_ID);
        return color == -1 ? null : ColorUtil.fromARGB(color);
    }

    @Override
    public void setGlowColorOverride(@Nullable Color color, int alpha) {
        this.entityData.set(DATA_GLOW_COLOR_OVERRIDE_ID, color == null ? -1 : ColorUtil.asARGB(color, alpha));
    }

    @Override
    public float getHeight() {
        return this.entityData.get(DATA_HEIGHT_ID);
    }

    static {
        if (Version.VERSION.olderThan(Version.V1_20_4)) {
            DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID = Mappings.findAccessor("Display", "DATA_INTERPOLATION_START_DELTA_TICKS_ID");
            DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID = Mappings.findAccessor("Display", "DATA_INTERPOLATION_DURATION_ID");
            DATA_POS_ROT_INTERPOLATION_DURATION_ID = null;
        } else {
            DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID = Mappings.findAccessor("Display", "DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID");
            DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID = Mappings.findAccessor("Display", "DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID");
            DATA_POS_ROT_INTERPOLATION_DURATION_ID = Mappings.findAccessor("Display", "DATA_POS_ROT_INTERPOLATION_DURATION_ID");
        }
        DATA_TRANSLATION_ID = Mappings.findAccessor("Display", "DATA_TRANSLATION_ID");
        DATA_SCALE_ID = Mappings.findAccessor("Display", "DATA_SCALE_ID");
        DATA_LEFT_ROTATION_ID = Mappings.findAccessor("Display", "DATA_LEFT_ROTATION_ID");
        DATA_RIGHT_ROTATION_ID = Mappings.findAccessor("Display", "DATA_RIGHT_ROTATION_ID");
        DATA_BILLBOARD_RENDER_CONSTRAINTS_ID = Mappings.findAccessor("Display", "DATA_BILLBOARD_RENDER_CONSTRAINTS_ID");
        DATA_BRIGHTNESS_OVERRIDE_ID = Mappings.findAccessor("Display", "DATA_BRIGHTNESS_OVERRIDE_ID");
        DATA_VIEW_RANGE_ID = Mappings.findAccessor("Display", "DATA_VIEW_RANGE_ID");
        DATA_SHADOW_RADIUS_ID = Mappings.findAccessor("Display", "DATA_SHADOW_RADIUS_ID");
        DATA_SHADOW_STRENGTH_ID = Mappings.findAccessor("Display", "DATA_SHADOW_STRENGTH_ID");
        DATA_WIDTH_ID = Mappings.findAccessor("Display", "DATA_WIDTH_ID");
        DATA_HEIGHT_ID = Mappings.findAccessor("Display", "DATA_HEIGHT_ID");
        DATA_GLOW_COLOR_OVERRIDE_ID = Mappings.findAccessor("Display", "DATA_GLOW_COLOR_OVERRIDE_ID");
    }
}