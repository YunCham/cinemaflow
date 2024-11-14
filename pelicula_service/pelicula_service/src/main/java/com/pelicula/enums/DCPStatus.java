package com.pelicula.enums;

import java.util.Arrays;

public enum DCPStatus {
        AVAILABLE,
        IN_USE,
        IN_TRANSIT,
        DAMAGED;

    public static boolean isValid(String role) {
        try {
            Role.valueOf(role.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String getValidRoles() {
        return String.join(", ", Arrays.stream(Role.values()).map(Enum::name).toList());
    }
}
