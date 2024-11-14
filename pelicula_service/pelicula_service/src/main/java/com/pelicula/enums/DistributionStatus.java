package com.pelicula.enums;

import java.util.Arrays;

public enum DistributionStatus {
    SCHEDULED,
    IN_PROGRESS,
    FINISHED;

//    private final String status;
//
//    DistributionStatus(String status) {
//        this.status = status;
//    }
//
//    public String getStatus() {
//        return status;
//    }

//    public static boolean isValid(String status) {
//        for (DistributionStatus ds : DistributionStatus.values()) {
//            if (ds.getStatus().equalsIgnoreCase(status)) {
//                return true;
//            }
//        }
//        return false;
//    }
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
