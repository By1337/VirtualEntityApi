package dev.by1337.virtualentity.api.virtual.projectile;

public interface VirtualThrownTrident extends VirtualAbstractArrow {
    byte getLoyalty();

    void setLoyalty(byte loyalty);

    boolean hasFoil();

    void setFoil(boolean foil);
}
