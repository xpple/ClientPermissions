package dev.xpple.clientpermissions.api;

import dev.xpple.clientpermissions.config.Configs;
import dev.xpple.clientpermissions.config.ResponsePolicy;
import dev.xpple.clientpermissions.handlers.ModListHandler;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

class ClientPermissionsImpl implements ClientPermissionsAPI {
    @Override
    public boolean isEnabled() {
        return Configs.enabled;
    }

    @Override
    public ResponsePolicy getResponsePolicy() {
        return Configs.responsePolicy;
    }

    @Override
    public List<String> getDisallowedMods() {
        return Collections.unmodifiableList(Configs.disallowedMods);
    }

    @Override
    public List<String> getDisallowedFeatures() {
        return Collections.unmodifiableList(Configs.disallowedFeatures);
    }

    @Override
    public List<String> getModsForPlayer(UUID player) {
        return Collections.unmodifiableList(ModListHandler.getModsForPlayer(player));
    }

    static final ClientPermissionsImpl INSTANCE = new ClientPermissionsImpl();
}
