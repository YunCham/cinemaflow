package com.pelicula.model;

import com.pelicula.enums.MaterialStatus;
import com.pelicula.enums.MaterialType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "promotional_material")
public class PromotionalMaterial {

    @Id
    private String id;

    @NotBlank(message = "Movie ID is mandatory")
    private String movieId;

    @NotNull(message = "Type is mandatory")
    private MaterialType type;

    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    @NotBlank(message = "Warehouse location is mandatory")
    private String warehouseLocation;

    @NotNull(message = "Status is mandatory")
    private MaterialStatus status;

    private List<Distribution> distributions;

    @Data
    public static class Distribution {
        @NotBlank(message = "Theater ID is mandatory")
        private String theaterId;

        @NotNull(message = "Quantity is mandatory")
        private Integer quantity;

        @NotNull(message = "Date is mandatory")
        private LocalDate date;

        public Distribution(String theaterId, Integer quantity, LocalDate date) {
            this.theaterId = theaterId;
            this.quantity = quantity;
            this.date = date;
        }

        public Distribution() {
        }
    }
}
