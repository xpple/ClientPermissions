package dev.xpple.clientpermissions.api;

import dev.xpple.clientpermissions.handlers.ModFeaturesHandler;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

class ClientPermissionsClientImpl implements ClientPermissionsClientAPI {
    @Override
    public List<String> getDisallowedFeatures() {
        return Collections.unmodifiableList(ModFeaturesHandler.features);
    }

    @Override
    public boolean isFeatureDisallowed(String feature) {
        return ModFeaturesHandler.features.contains(feature.toLowerCase(Locale.ROOT));
    }

    static final ClientPermissionsClientImpl INSTANCE = new ClientPermissionsClientImpl();
}
