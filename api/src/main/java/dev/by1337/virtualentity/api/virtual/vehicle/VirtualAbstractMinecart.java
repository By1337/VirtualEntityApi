package dev.by1337.virtualentity.api.virtual.vehicle;

import dev.by1337.virtualentity.api.virtual.VirtualEntity;
import org.bukkit.block.data.BlockData;

public interface VirtualAbstractMinecart extends VirtualEntity {
    float getDamage();

    void setDamage(float damage);

    int getHurt();

    void setHurt(int hurt);

    int getHurtDirection();

    void setHurtDirection(int hurtDir);

    int getDisplayBlock();

    void setDisplayBlock(BlockData displayBlock);

    int getDisplayOffset();

    void setDisplayOffset(int offset);

    boolean hasCustomDisplay();

    void setCustomDisplay(boolean customDisplay);
}
