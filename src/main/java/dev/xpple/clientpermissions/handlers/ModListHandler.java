package dev.xpple.clientpermissions.handlers;

import com.google.common.base.Joiner;
import dev.xpple.clientpermissions.config.Configs;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.*;

import static dev.xpple.clientpermissions.ClientPermissions.FEATURES_CHANNEL;

public class ModListHandler implements ServerPlayNetworking.PlayChannelHandler {

    private static final Map<UUID, List<String>> modsForPlayer = new HashMap<>();

    public static List<String> getModsForPlayer(UUID player) {
        return modsForPlayer.getOrDefault(player, Collections.emptyList());
    }

    @Override
    public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        List<String> mods = new ArrayList<>();
        int length = buf.readInt();
        for (int i = 0; i < length; i++) {
            String modId = buf.readString();
            mods.add(modId);
        }
        modsForPlayer.put(player.getUuid(), new ArrayList<>(mods));

        mods.retainAll(Configs.disallowedMods);

        if (!mods.isEmpty()) {
            switch (Configs.responsePolicy) {
                case KICK -> {
                    player.networkHandler.disconnect(createWarnText(mods));
                    return;
                }
                case WARN -> player.sendMessage(createWarnText(mods).formatted(Formatting.RED));
            }
        }

        List<String> disallowedFeatures = Configs.disallowedFeatures;

        PacketByteBuf responseBuf = PacketByteBufs.create();
        responseBuf.writeInt(disallowedFeatures.size());
        disallowedFeatures.forEach(responseBuf::writeString);

        responseSender.sendPacket(FEATURES_CHANNEL, responseBuf);
    }

    private static MutableText createWarnText(List<String> modIds) {
        // TODO: 28/01/2023 put some more work into this
        if (modIds.size() == 1) {
            return Text.translatable("clientpermissions.illegalModsWarning.single", modIds.iterator().next());
        }
        if (modIds.size() <= 5) {
            return Text.translatable("clientpermissions.illegalModsWarning.fiveOrLess", Joiner.on(", ").join(modIds));
        }
        return Text.translatable("clientpermissions.illegalModsWarning.many", Joiner.on(", ").join(modIds.subList(0, 5)));
    }
}
