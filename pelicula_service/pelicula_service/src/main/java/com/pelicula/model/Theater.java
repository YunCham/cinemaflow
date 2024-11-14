package com.pelicula.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "theaters")
public class Theater {
    @Id
    private String id;

    @NotBlank(message = "Theater name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Contact person is mandatory")
    private String contactPerson;

    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotNull(message = "Theater status is mandatory")
    private Boolean isActive;

    private List<Room> rooms;

    @Data
    public static class Room {
        @NotBlank(message = "Room number is mandatory")
        private String number;

        @NotNull(message = "Room capacity is mandatory")
        private Integer capacity;


        public Room(String number, Integer capacity) {
            this.number = number;
            this.capacity = capacity;
        }

        // Constructor vacío si lo necesitas
        public Room() {}
    }
}
