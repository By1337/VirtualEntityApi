package dev.by1337.virtualentity.core.mappings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.by1337.core.ServerVersion;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class Packets {
    public static final Flows configuration = new Flows();
    public static final Flows handshake = new Flows();
    public static final Flows login = new Flows();
    public static final Flows play = new Flows();
    public static final Flows status = new Flows();

    public static void load() {
    }


    public static class Flows {
        public final IdHolder serverbound = new IdHolder();
        public final IdHolder clientbound = new IdHolder();
    }

    public static class IdHolder {
        private final Object2IntMap<String> key2id;

        public IdHolder() {
            key2id = new Object2IntOpenHashMap<>();
            key2id.defaultReturnValue(-1);
        }

        public int getId(String key) {
            return key2id.getInt(key);
        }
    }

    static {
        try (var in = new InputStreamReader(getMappingsInputStream("packets/" + ServerVersion.CURRENT_PROTOCOL + ".json"))) {
            Map<String, Map<String, Map<String, Map<String, Integer>>>> rawMap =
                    new Gson().fromJson(in, new TypeToken<Map<String, Map<String, Map<String, Map<String, Integer>>>>>() {}.getType());

            loadFlow(rawMap.get("configuration"), configuration);
            loadFlow(rawMap.get("handshake"), handshake);
            loadFlow(rawMap.get("login"), login);
            loadFlow(rawMap.get("play"), play);
            loadFlow(rawMap.get("status"), status);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadFlow(Map<String, Map<String, Map<String, Integer>>> raw, Flows to) {
        if (raw == null) return;
        loadTo(raw.get("serverbound"), to.serverbound);
        loadTo(raw.get("clientbound"), to.clientbound);
    }

    private static void loadTo(Map<String, Map<String, Integer>> raw, IdHolder to) {
        if (raw == null) return;
        for (String s : raw.keySet()) {
            to.key2id.put(s, (int) raw.get(s).get("protocol_id"));
        }
    }

    @NotNull
    public static InputStream getMappingsInputStream(String file) {
        InputStream in;
        ClassLoader loader = Packets.class.getClassLoader();
        URL url = loader.getResource(file);
        if (url == null) {
            throw new RuntimeException("Could not find mappings file " + file);
        }
        try {
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            in = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (in == null) {
            throw new RuntimeException("Could not find mappings file " + file);
        }
        return in;
    }
}
