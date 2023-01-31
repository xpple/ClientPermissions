package dev.xpple.clientpermissions.config;

import java.util.List;

public class Configs {
    public static final boolean enabled = ConfigHelper.initEnabled();

    public static final ResponsePolicy responsePolicy = ConfigHelper.initResponsePolicy();

    public static final List<String> disallowedMods = ConfigHelper.initDisallowedMods();

    public static final List<String> disallowedFeatures = ConfigHelper.initDisallowedFeatures();
}
