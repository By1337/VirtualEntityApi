package dev.by1337.virtualentity.dumper;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class MinecraftDownloader {

    //@Test
    public void run() {
        //java "-DbundlerMainClass=net.minecraft.data.Main" -jar ./server.jar --all
        Gson gson = new Gson();
        Manifest manifest = gson.fromJson(urlReader("https://launchermeta.mojang.com/mc/game/version_manifest.json"), Manifest.class);
        for (Manifest.Version version : manifest.versions) {
            if (version.type.equals("release")) {
                VersionInfo info = gson.fromJson(urlReader(version.url), VersionInfo.class);
                var server = info.downloads.get("server").url;
                System.out.println(
                        "wget " + server + " && mkdir " + version.id + " && mv ./server.jar ./" + version.id + "/server.jar"
                );
            }
        }
    }

    public static class VersionInfo {
        public String id;
        public Map<String, Download> downloads;

        public static class Download {
            public String sha1;
            public int size;
            public String url;
        }
    }

    public static class Manifest {
        public Latest latest;
        public List<Version> versions;

        public static class Version {
            public String id;
            public String type;
            public String url;
            public String time;
            public String releaseTime;
        }

        public static class Latest {
            public String release;
            public String snapshot;
        }
    }


    public static Reader urlReader(String url) {
        try {
            URLConnection connection = URI.create(url).toURL().openConnection();
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
