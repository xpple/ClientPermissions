package dev.xpple.clientpermissions;

import dev.xpple.clientpermissions.config.ConfigHelper;
import dev.xpple.clientpermissions.config.Configs;
import dev.xpple.clientpermissions.handlers.ModListHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

public class ClientPermissions implements ModInitializer {

    public static final String MOD_ID = "clientpermissions";
    public static final Path MOD_PATH = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);

    public static final Identifier MODS_CHANNEL = new Identifier(MOD_ID, "mods");
    public static final Identifier FEATURES_CHANNEL = new Identifier(MOD_ID, "features");

    @Override
    public void onInitialize() {
        //noinspection ResultOfMethodCallIgnored
        MOD_PATH.toFile().mkdir();

        ConfigHelper.init();

        if (Configs.enabled) {
            ServerPlayNetworking.registerGlobalReceiver(MODS_CHANNEL, new ModListHandler());
        }
    }
}
