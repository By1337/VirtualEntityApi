package dev.by1337.virtualentity.core.network.impl;

import com.mojang.authlib.properties.PropertyMap;
import dev.by1337.virtualentity.api.annotations.RemovedInMinecraftVersion;
import dev.by1337.virtualentity.api.virtual.player.VirtualPlayer;
import dev.by1337.virtualentity.core.mappings.Packets;
import dev.by1337.virtualentity.core.network.ByteBufUtil;
import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.virtual.player.VirtualPlayerImpl;
import io.netty.buffer.ByteBuf;
import org.bukkit.GameMode;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.BiConsumer;

public class PlayerInfoPacket extends Packet {
    private static final int REMOVE_PLAYER_PACKET = Packets.play.clientbound.getId("minecraft:player_info_remove");
    private static final int UPDATE_PLAYER_INFO_PACKET = Packets.play.clientbound.getId("minecraft:player_info_update");

    private final VirtualPlayer player;
    private final Action[] actions;

    public PlayerInfoPacket(VirtualPlayerImpl player, Action... actions) {
        this((VirtualPlayer) player, actions);
    }

    public PlayerInfoPacket(VirtualPlayer player, Action... actions) {
        this.player = player;
        if (actions.length == 0) {
            throw new IllegalArgumentException("Actions must contain at least one action");
        }
        EnumSet<Action> actionSet = EnumSet.copyOf(Arrays.asList(actions));
        if (actionSet.contains(Action.REMOVE_PLAYER) && actionSet.size() != 1) {
            throw new IllegalArgumentException("REMOVE_PLAYER cannot be combined with update actions");
        }
        // The client's EnumSet decoder reads each action once, in enum order.
        this.actions = actionSet.toArray(new Action[0]);
    }

    @Override
    public void write(ByteBuf byteBuf) {
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
    }

    private static void writeGameProfileProperties(ByteBuf byteBuf, VirtualPlayer player) {
        PropertyMap propertyMap = player.getProperties();
        ByteBufUtil.writeVarInt(propertyMap.size(), byteBuf);
        propertyMap.values().forEach(value -> {
            ByteBufUtil.writeUtf(value.name(), byteBuf);
            ByteBufUtil.writeUtf(value.value(), byteBuf);
            ByteBufUtil.writeOptional(byteBuf, value.signature(), ByteBufUtil::writeUtf);
        });
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
            ByteBufUtil.writeUtf(player.getName(), byteBuf);
            writeGameProfileProperties(byteBuf, player);
        }),

        INITIALIZE_CHAT((byte) (1 << 1), (byteBuf, player) -> {
            byteBuf.writeBoolean(false); // no RemoteChatSession.Data for a virtual player
        }),
        UPDATE_GAME_MODE((byte) (1 << 2), (byteBuf, player) -> ByteBufUtil.writeVarInt(toId(player.getGameMode()), byteBuf)),

        UPDATE_LISTED((byte) (1 << 3), (byteBuf, player) -> {
            byteBuf.writeBoolean(player.isListed());
        }),
        UPDATE_LATENCY((byte) (1 << 4), (byteBuf, player) -> ByteBufUtil.writeVarInt(player.getLatency(), byteBuf)),
        UPDATE_DISPLAY_NAME((byte) (1 << 5), (byteBuf, player) -> ByteBufUtil.writeOptional(byteBuf, player.getDisplayName(), ByteBufUtil::writeComponent)),

        UPDATE_LIST_ORDER((byte) (1 << 6), (byteBuf, player) -> {
            ByteBufUtil.writeVarInt(player.getListOrder(), byteBuf);
        }),
        @RemovedInMinecraftVersion("1.19.4")
        REMOVE_PLAYER((byte) 0, (byteBuf, player) -> {
            throw new UnsupportedOperationException("REMOVE_PLAYER uses its own packet");
        });

        private final byte mask;
        private final BiConsumer<ByteBuf, VirtualPlayer> writer;

        Action(byte mask, BiConsumer<ByteBuf, VirtualPlayer> writer) {
            this.mask = mask;
            this.writer = writer;
        }

        public int getId() {
            if (this == REMOVE_PLAYER) throw new IllegalStateException("Not supported in this version!");
            return ordinal();
        }
    }

    @Override
    public String toString() {
        return "PlayerInfoPacket{" +
                "player=" + player +
                ", actions=" + Arrays.toString(actions) +
                '}';
    }
}
