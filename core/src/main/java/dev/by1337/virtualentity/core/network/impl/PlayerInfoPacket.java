package dev.by1337.virtualentity.core.network.impl;

import com.mojang.authlib.properties.PropertyMap;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.annotations.SinceMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.player.VirtualPlayer;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.network.PacketType;
import dev.by1337.virtualentity.core.virtual.player.VirtualPlayerImpl;
import io.netty.buffer.ByteBuf;
import org.bukkit.GameMode;
import org.by1337.blib.util.Version;

import java.util.function.BiConsumer;

public class PlayerInfoPacket extends Packet {
    private static final boolean IS_1_19_4_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_19_4);
    private static final boolean IS_1_21_3_OR_NEWER = Version.VERSION.newerThanOrEqual(Version.V1_21_3);
    private static final int PLAYER_INFO_PACKET = PacketType.PLAYER_INFO_PACKET.getId(-1);
    private static final int REMOVE_PLAYER_PACKET = PacketType.REMOVE_PLAYER_PACKET.getId(-1);
    private static final int UPDATE_PLAYER_INFO_PACKET = PacketType.UPDATE_PLAYER_INFO_PACKET.getId(-1);

    private final VirtualPlayerImpl player;
    private final Action[] actions;

    public PlayerInfoPacket(VirtualPlayerImpl player, Action... actions) {
        this.player = player;
        this.actions = actions;
        if (!IS_1_19_4_OR_NEWER && actions.length != 1) {
            throw new IllegalArgumentException("Actions must contain exactly one action");
        }
        if (actions.length == 0) {
            throw new IllegalArgumentException("Actions must contain at least one action");
        }
    }

    @Override
    public void write(ByteBuf byteBuf) {
        if (IS_1_19_4_OR_NEWER) {
            if (actions[0] == Action.REMOVE_PLAYER) {
                ByteBufUtil.writeVarInt(REMOVE_PLAYER_PACKET, byteBuf);
                ByteBufUtil.writeVarInt(1, byteBuf); // players count
                ByteBufUtil.writeUUID(player.getUuid(), byteBuf);
            } else {
                ByteBufUtil.writeVarInt(UPDATE_PLAYER_INFO_PACKET, byteBuf);
                byte data = 0;
                for (Action action : actions) {
                    data |= action.mask;
                }
                byteBuf.writeByte(data);
                ByteBufUtil.writeVarInt(1, byteBuf); // players count

                ByteBufUtil.writeUUID(player.getUuid(), byteBuf);

                for (Action action : actions) {
                    action.writer.accept(byteBuf, player);
                }

            }
        } else {
            writeLegacy(byteBuf);
        }
    }

    private static void writeGameProfileProperties(ByteBuf byteBuf, VirtualPlayer player) {
        PropertyMap propertyMap = player.getProperties();
        ByteBufUtil.writeVarInt(propertyMap.size(), byteBuf);
        propertyMap.forEach((key, value) -> {
            ByteBufUtil.writeUtf(key, byteBuf);
            ByteBufUtil.writeUtf(value.getValue(), byteBuf);
            ByteBufUtil.writeOptional(byteBuf, value.getSignature(), ByteBufUtil::writeUtf);
        });
    }

    private void writeLegacy(ByteBuf byteBuf) {
        ByteBufUtil.writeVarInt(PLAYER_INFO_PACKET, byteBuf);
        ByteBufUtil.writeVarInt(actions[0].getId(), byteBuf);
        ByteBufUtil.writeVarInt(1, byteBuf); // players count
        ByteBufUtil.writeUUID(player.getUuid(), byteBuf);

        actions[0].writer.accept(byteBuf, player);
    }

    private static int toId(GameMode gameMode) {
        return switch (gameMode) {
            case SURVIVAL -> 0;
            case CREATIVE -> 1;
            case ADVENTURE -> 2;
            case SPECTATOR -> 3;
        };
    }

    public enum Action {
        ADD_PLAYER((byte) 1, (byteBuf, player) -> {
            if (IS_1_19_4_OR_NEWER) {
                ByteBufUtil.writeUtf(player.getName(), byteBuf);
                writeGameProfileProperties(byteBuf, player);
            } else {
                ByteBufUtil.writeUtf(player.getName(), byteBuf);
                writeGameProfileProperties(byteBuf, player);
                ByteBufUtil.writeVarInt(toId(player.getGameMode()), byteBuf);
                ByteBufUtil.writeVarInt(player.getLatency(), byteBuf);
                ByteBufUtil.writeOptional(byteBuf, player.getDisplayName(), ByteBufUtil::writeComponent);
            }
        }),
        @SinceMinecraftVersion("1.19.4")
        INITIALIZE_CHAT((byte) (1 << 1), (byteBuf, player) -> {
            if (IS_1_19_4_OR_NEWER) {
            } else {
                throw new UnsupportedOperationException("The INITIALIZE_CHAT operation is not supported in version " + Version.VERSION.getVer());
            }
        }),
        UPDATE_GAME_MODE((byte) (1 << 2), (byteBuf, player) -> ByteBufUtil.writeVarInt(toId(player.getGameMode()), byteBuf)),
        @SinceMinecraftVersion("1.19.4")
        UPDATE_LISTED((byte) (1 << 3), (byteBuf, player) -> {
            if (IS_1_19_4_OR_NEWER) {
                byteBuf.writeBoolean(player.isListed());
            } else {
                throw new UnsupportedOperationException("The UPDATE_LISTED operation is not supported in version " + Version.VERSION.getVer());
            }
        }),
        UPDATE_LATENCY((byte) (1 << 4), (byteBuf, player) -> ByteBufUtil.writeVarInt(player.getLatency(), byteBuf)),
        UPDATE_DISPLAY_NAME((byte) (1 << 5), (byteBuf, player) -> ByteBufUtil.writeOptional(byteBuf, player.getDisplayName(), ByteBufUtil::writeComponent)),
        @SinceMinecraftVersion("1.21.3")
        UPDATE_LIST_ORDER((byte) (1 << 6), (byteBuf, player) -> {
            if (IS_1_21_3_OR_NEWER) {
                ByteBufUtil.writeVarInt(player.getListOrder(), byteBuf);
            } else {
                throw new UnsupportedOperationException("The UPDATE_LIST_ORDER operation is not supported in version " + Version.VERSION.getVer());
            }
        }),
        @RemovedInMinecraftVersion("1.19.4")
        REMOVE_PLAYER((byte) 0, (byteBuf, player) -> {
            if (IS_1_19_4_OR_NEWER) {
                throw new UnsupportedOperationException("The REMOVE_PLAYER operation is not supported in version " + Version.VERSION.getVer());
            } else {
                // none
            }
        });

        private final byte mask;
        private final BiConsumer<ByteBuf, VirtualPlayer> writer;

        Action(byte mask, BiConsumer<ByteBuf, VirtualPlayer> writer) {
            this.mask = mask;
            this.writer = writer;
        }

        public int getId() {
            if (IS_1_19_4_OR_NEWER) {
                if (this == REMOVE_PLAYER) throw new IllegalStateException("Not supported in this version!");
                return ordinal();
            }
            return switch (this) {
                case ADD_PLAYER -> 0;
                case INITIALIZE_CHAT, UPDATE_LISTED, UPDATE_LIST_ORDER ->
                        throw new IllegalStateException("Not supported in this version!");
                case UPDATE_GAME_MODE -> 1;
                case UPDATE_LATENCY -> 2;
                case UPDATE_DISPLAY_NAME -> 3;
                case REMOVE_PLAYER -> 4;
            };
        }
    }
}
