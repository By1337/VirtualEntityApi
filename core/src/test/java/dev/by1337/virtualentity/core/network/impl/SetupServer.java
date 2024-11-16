package dev.by1337.virtualentity.core.network.impl;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.UnsafeValues;
import org.mockito.Mockito;

import java.util.logging.Logger;

public class SetupServer {

    @SuppressWarnings("all")
    public static void setup() {
        if (Bukkit.getServer() != null) return;
        Server server = Mockito.mock(Server.class);
        UnsafeValues unsafeValues = Mockito.mock(UnsafeValues.class);
        Mockito.when(unsafeValues.nextEntityId()).thenReturn(1);
        Mockito.when(server.getUnsafe()).thenReturn(unsafeValues);
        Mockito.when(server.getLogger()).thenReturn(Logger.getLogger("Mockito Server"));
        Bukkit.setServer(server);
    }
}
