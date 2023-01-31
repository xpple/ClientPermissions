package dev.xpple.clientpermissions.api;

import dev.xpple.clientpermissions.config.ResponsePolicy;

import java.util.List;
import java.util.UUID;

public interface ClientPermissionsAPI {
    static ClientPermissionsAPI getInstance() {
        return ClientPermissionsImpl.INSTANCE;
    }

    /**
     * Returns whether ClientPermissions is enabled on this server.
     * @return {@code true} if ClientPermissions is enabled, {@code false} otherwise
     */
    boolean isEnabled();

    /**
     * The current response policy to illegal mods. The options are:
     *
     * <ul>
     *     <li>{@link ResponsePolicy#KICK} - kick players that are using illegal mods.</li>
     *     <li>
     *         {@link ResponsePolicy#WARN} - warn players that are using illegal mods.
     *         Let the client decide what to do with the warning. This works well if
     *         the client is using mods that locally disable their features on request.
     *     </li>
     * </ul>
     * @return the current response policy
     */
    ResponsePolicy getResponsePolicy();

    /**
     * The list of disallowed mods as specified by the config file.
     * @return the list of disallowed mods
     */
    List<String> getDisallowedMods();

    /**
     * The list of disallowed features as specified by the config file.
     * @return the list of disallowed features
     */
    List<String> getDisallowedFeatures();

    /**
     * A list of mods the specified player is currently using.
     * @param player the player
     * @return a list of mods, or an empty list if the player wasn't found
     */
    List<String> getModsForPlayer(UUID player);
}
