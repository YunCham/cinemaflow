package com.pelicula.dto;

import com.pelicula.enums.MovieStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;
public record MovieRequest(


        String id,

        @NotBlank(message = "Title is mandatory")
        String title,

        @NotBlank(message = "Genre is mandatory")
        String genre,

        @NotNull(message = "Release date is mandatory")
        LocalDate releaseDate,

        @NotBlank(message = "Classification is mandatory")
        String classification,

        @NotBlank(message = "Duration is mandatory")
        String duration,

        @NotBlank(message = "Distributor is mandatory")
        String distributor,

        @NotNull(message = "Status is mandatory")
        MovieStatus status,

        String synopsis,

        Double marketingBudget,
        List<String> availableLanguages
) {
}
