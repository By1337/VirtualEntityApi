package dev.by1337.virtualentity.core.api;

import dev.by1337.virtualentity.core.network.Packet;
import dev.by1337.virtualentity.core.virtual.VirtualEntityControllerImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * This interface is used to listen to all packets sent by
 * {@link dev.by1337.virtualentity.api.virtual.VirtualEntity}.
 *
 * @see VirtualEntityControllerImpl#setPacketListener(PacketListener)
 */
@Nullable
public interface PacketListener {
    /**
     * Called when a packet is about to be sent.
     *
     * @param player the player to whom the packet is being sent
     * @param packet the packet to be sent
     * @return the packet to be sent, or null to prevent sending it
     */
    @Nullable Packet onSend(Player player, Packet packet);
}
