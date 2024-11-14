package com.pelicula.dto;

import java.time.LocalDate;
import java.util.List;

public record PromotionalMaterialRequest(
        String id,
        String movieId,
        String type, // Enum value as String
        Integer quantity,
        String warehouseLocation,
        String status, // Enum value as String
        List<DistributionRecord> distributions
) {
    public record DistributionRecord(
            String theaterId,
            Integer quantity,
            LocalDate date
    ) {
    }
}
