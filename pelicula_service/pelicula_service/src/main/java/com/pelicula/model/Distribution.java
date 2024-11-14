package com.pelicula.model;

import com.pelicula.enums.DistributionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "distributions")
public class Distribution {

    @Id
    private String id;

    @NotBlank(message = "User ID is mandatory")
    private String userId;

    @NotBlank(message = "Movie ID is mandatory")
    private String movieId;

    @NotBlank(message = "Theater ID is mandatory")
    private String theaterId;

    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
    private LocalDate endDate;

    @NotBlank(message = "Status is mandatory")
    private DistributionStatus status;

    private Double revenue;  // Optional field

    private Integer viewers;  // Optional field

//    @NotBlank(message = "AssignedDCP ID is mandatory")
    private String assignedDCPId;  // Optional field, refers to a DCP ID
}