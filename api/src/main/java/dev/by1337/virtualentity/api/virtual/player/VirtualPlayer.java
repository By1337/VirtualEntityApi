package dev.by1337.virtualentity.api.virtual.player;

import com.mojang.authlib.properties.PropertyMap;
import dev.by1337.virtualentity.api.VirtualEntityApi;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.api.virtual.VirtualLivingEntity;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.by1337.blib.nbt.impl.CompoundTag;
import org.jetbrains.annotations.Nullable;

public interface VirtualPlayer extends VirtualAvatar {
    float getPlayerAbsorption();

    void setPlayerAbsorption(float absorption);

    int getScore();

    void setScore(int score);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.9")
    CompoundTag getShoulderLeft();

    @Deprecated
    @RemovedInMinecraftVersion("1.21.9")
    void setShoulderLeft(CompoundTag tag);

    @Deprecated
    @RemovedInMinecraftVersion("1.21.9")
    CompoundTag getShoulderRight();

    @Deprecated
    @RemovedInMinecraftVersion("1.21.9")
    void setShoulderRight(CompoundTag tag);

    void removeTexture();

    void setTexture(String value, String signature);

    void sendAddPlayerPacket(Player player);

    void sendRemovePlayerPacket(Player player);

    @Nullable Component getDisplayName();

    void setDisplayName(@Nullable Component displayName);

    int getLatency();

    void setLatency(int latency);

    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    PropertyMap getProperties();

    String getName();

    void setName(String name);

    @SinceMinecraftVersion("1.19.4")
    default void hideFromTab() {
        setListed(false);
    }

    @SinceMinecraftVersion("1.19.4")
    default void showInTab() {
        setListed(true);
    }

    @SinceMinecraftVersion("1.19.4")
    boolean isListed();

    @SinceMinecraftVersion("1.19.4")
    void setListed(boolean listed);

    @SinceMinecraftVersion("1.21.3")
    int getListOrder();

    @SinceMinecraftVersion("1.21.3")
    void setListOrder(int val);

    static VirtualPlayer create() {
        return VirtualEntityApi.getFactory().create(VirtualEntityType.PLAYER, VirtualPlayer.class);
    }
}
