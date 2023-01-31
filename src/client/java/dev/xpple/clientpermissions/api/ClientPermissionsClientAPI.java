package dev.xpple.clientpermissions.api;

import java.util.List;

public interface ClientPermissionsClientAPI {
    static ClientPermissionsClientAPI getInstance() {
        return ClientPermissionsClientImpl.INSTANCE;
    }

    /**
     * A list of disallowed features as imposed by the server the
     * client is currently connected to.
     * @return a list of disallowed features
     */
    List<String> getDisallowedFeatures();

    /**
     * Checks whether a feature is disallowed as imposed by the server
     * the client is currently connected to.
     * @param feature the feature to check
     * @return {@code true} if this feature is disallowed, {@code false} otherwise
     */
    boolean isFeatureDisallowed(String feature);
}
