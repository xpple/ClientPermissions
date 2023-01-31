package dev.xpple.clientpermissions.handlers;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

import java.util.ArrayList;
import java.util.List;

public class ModFeaturesHandler implements ClientPlayNetworking.PlayChannelHandler {

    public static List<String> features = new ArrayList<>();

    @Override
    public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        List<String> features = new ArrayList<>();
        int length = buf.readInt();
        for (int i = 0; i < length; i++) {
            String feature = buf.readString();
            features.add(feature);
        }
        ModFeaturesHandler.features = features;
    }
}
