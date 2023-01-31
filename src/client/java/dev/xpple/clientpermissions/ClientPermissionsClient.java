package dev.xpple.clientpermissions;

import dev.xpple.clientpermissions.handlers.ModFeaturesHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.PacketByteBuf;

import java.util.Collection;

import static dev.xpple.clientpermissions.ClientPermissions.FEATURES_CHANNEL;
import static dev.xpple.clientpermissions.ClientPermissions.MODS_CHANNEL;

public class ClientPermissionsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();

            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(mods.size());
            mods.forEach(mod -> buf.writeString(mod.getMetadata().getId()));

            sender.sendPacket(MODS_CHANNEL, buf);
        });

        ClientPlayNetworking.registerGlobalReceiver(FEATURES_CHANNEL, new ModFeaturesHandler());
    }
}
