package com.pelicula.dto;

import com.pelicula.enums.DistributionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DistributionRequest(

        @NotBlank(message = "User ID is mandatory")
        String userId,

        @NotBlank(message = "Movie ID is mandatory")
        String movieId,

        @NotBlank(message = "Theater ID is mandatory")
        String theaterId,

        @NotNull(message = "Start date is mandatory")
        String startDate,

        @NotNull(message = "End date is mandatory")
        String endDate,

        @NotBlank(message = "Status is mandatory")
        DistributionStatus status,

        String assignedDCPId,

        Double revenue,

        Integer viewers,

        String assignedDCP
) {
}
