package dev.by1337.virtualentity.api.util;

import com.google.common.base.Preconditions;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * Represents an arbitrary affine transformation.
 */
public class Transformation {
    private static final Transformation IDENTITY = new Transformation(new Vector3f(), new Quaternionf(), new Vector3f(1f, 1f, 1f), new Quaternionf());

    @NotNull
    private final Vector3f translation;
    @NotNull
    private final Quaternionf leftRotation;
    @NotNull
    private final Vector3f scale;
    @NotNull
    private final Quaternionf rightRotation;

    public Transformation(@NotNull Vector3f translation, @NotNull AxisAngle4f leftRotation, @NotNull Vector3f scale, @NotNull AxisAngle4f rightRotation) {
        this.translation = translation;
        this.leftRotation = new Quaternionf(leftRotation);
        this.scale = scale;
        this.rightRotation = new Quaternionf(rightRotation);
    }

    public Transformation(@NotNull Vector3f translation, @NotNull Quaternionf leftRotation, @NotNull Vector3f scale, @NotNull Quaternionf rightRotation) {
        this.translation = translation;
        this.leftRotation = leftRotation;
        this.scale = scale;
        this.rightRotation = rightRotation;
    }
    public static Transformation identity() {
        return IDENTITY;
    }

    public Transformation slerp(Transformation other, float f) {
        Vector3f translation1 = this.getTranslation();
        Quaternionf leftRotation1 = this.getLeftRotation();
        Vector3f scale1 = this.getScale();
        Quaternionf rightRotation1 = this.getRightRotation();
        translation1.lerp(other.getTranslation(), f);
        leftRotation1.slerp(other.getLeftRotation(), f);
        scale1.lerp(other.getScale(), f);
        rightRotation1.slerp(other.getRightRotation(), f);
        return new Transformation(translation1, leftRotation1, scale1, rightRotation1);
    }

    /**
     * Gets the translation component of this transformation.
     *
     * @return translation component
     */
    @NotNull
    public Vector3f getTranslation() {
        return this.translation;
    }

    /**
     * Gets the left rotation component of this transformation.
     *
     * @return left rotation component
     */
    @NotNull
    public Quaternionf getLeftRotation() {
        return this.leftRotation;
    }

    /**
     * Gets the scale component of this transformation.
     *
     * @return scale component
     */
    @NotNull
    public Vector3f getScale() {
        return this.scale;
    }

    /**
     * Gets the right rotation component of this transformation.
     *
     * @return right rotation component
     */
    @NotNull
    public Quaternionf getRightRotation() {
        return this.rightRotation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.translation);
        hash = 11 * hash + Objects.hashCode(this.leftRotation);
        hash = 11 * hash + Objects.hashCode(this.scale);
        hash = 11 * hash + Objects.hashCode(this.rightRotation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transformation other = (Transformation) obj;
        if (!Objects.equals(this.translation, other.translation)) {
            return false;
        }
        if (!Objects.equals(this.leftRotation, other.leftRotation)) {
            return false;
        }
        if (!Objects.equals(this.scale, other.scale)) {
            return false;
        }
        return Objects.equals(this.rightRotation, other.rightRotation);
    }

    @Override
    public String toString() {
        return "Transformation{" + "translation=" + this.translation + ", leftRotation=" + this.leftRotation + ", scale=" + this.scale + ", rightRotation=" + this.rightRotation + '}';
    }
}
