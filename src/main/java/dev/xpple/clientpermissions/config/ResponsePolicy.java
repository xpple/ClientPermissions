package dev.xpple.clientpermissions.config;

import com.google.gson.annotations.SerializedName;

public enum ResponsePolicy {
    @SerializedName("kick")
    KICK,
    @SerializedName("warn")
    WARN;
}
