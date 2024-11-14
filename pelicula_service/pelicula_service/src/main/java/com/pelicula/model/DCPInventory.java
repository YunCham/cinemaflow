package com.pelicula.model;

import com.pelicula.enums.DCPStatus;
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
@Document(collection = "dcp_inventory")
public class DCPInventory {
    @Id
    private String id;

    @NotBlank(message = "Movie ID is mandatory")
    private String movieId;

    @NotBlank(message = "DCP code is mandatory")
    private String code;

    @NotNull(message = "Status is mandatory")
    private DCPStatus status;

    @NotNull(message = "Reception date is mandatory")
    private LocalDate receptionDate;

    private String location;

    private List<DCPHistory> history;


    @Data
    public static class DCPHistory {
        @NotNull(message = "Date is mandatory")
        private LocalDate date;

        @NotBlank(message = "Status is mandatory")
        private String status;

        private String description;

        public DCPHistory(LocalDate date, String status, String description) {
            this.date = date;
            this.status = status;
            this.description = description;
        }

        public DCPHistory() {
        }
    }
}
