package com.pelicula.model;

import com.pelicula.enums.MovieStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @NotNull(message = "Release date is mandatory")
    private LocalDate releaseDate;

    @NotBlank(message = "Classification is mandatory")
    private String classification;

    @NotBlank(message = "Duration is mandatory")
    private String duration;

    @NotBlank(message = "Distributor is mandatory")
    private String distributor;

    @NotNull(message = "Status is mandatory")

    private MovieStatus status;

    private String synopsis;

    private Double marketingBudget;

    private List<String> availableLanguages;

    // Getters y Setters
}