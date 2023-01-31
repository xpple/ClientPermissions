package dev.xpple.clientpermissions.config;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static dev.xpple.clientpermissions.ClientPermissions.MOD_ID;
import static dev.xpple.clientpermissions.ClientPermissions.MOD_PATH;

public class ConfigHelper {

    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static final Path CONFIG_PATH = MOD_PATH.resolve("config.json");

    private static final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private static JsonObject root = new JsonObject();

    public static void init() {
        LOGGER.info("Loading config files...");
        try {
            if (CONFIG_PATH.toFile().isFile()) {
                root = JsonParser.parseReader(Files.newBufferedReader(CONFIG_PATH)).getAsJsonObject();
            } else {
                String standardJson = """
                    {
                      "enabled": true,
                      "responsePolicy": "kick",
                      "disallowedMods": [],
                      "disallowedFeatures": []
                    }
                    """;
                root = JsonParser.parseString(standardJson).getAsJsonObject();
                try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
                    writer.write(gson.toJson(root));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Could not load config files.");
            return;
        }
        LOGGER.info("Loaded config files successfully!");
    }

    static boolean initEnabled() {
        if (root.has("enabled")) {
            return root.get("enabled").getAsBoolean();
        }
        root.addProperty("enabled", true);
        return true;
    }

    static ResponsePolicy initResponsePolicy() {
        if (root.has("responsePolicy")) {
            return gson.fromJson(root.get("responsePolicy"), new TypeToken<ResponsePolicy>() {}.getType());
        }
        root.add("responsePolicy", new JsonArray());
        return ResponsePolicy.KICK;
    }

    static List<String> initDisallowedMods() {
        if (root.has("disallowedMods")) {
            return new ArrayList<>(gson.fromJson(root.getAsJsonArray("disallowedMods"), new TypeToken<List<String>>() {}.getType()));
        }
        root.add("disallowedMods", new JsonArray());
        return new ArrayList<>();
    }

    static List<String> initDisallowedFeatures() {
        if (root.has("disallowedFeatures")) {
            return new ArrayList<>(gson.fromJson(root.getAsJsonArray("disallowedFeatures"), new TypeToken<List<String>>() {}.getType()));
        }
        root.add("disallowedFeatures", new JsonArray());
        return new ArrayList<>();
    }
}
