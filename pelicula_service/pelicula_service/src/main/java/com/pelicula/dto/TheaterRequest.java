package com.pelicula.dto;

import com.pelicula.model.Theater;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record TheaterRequest(
        String id,

        @NotBlank(message = "Theater name is mandatory")
        String name,

        @NotBlank(message = "Address is mandatory")
        String address,

        @NotBlank(message = "City is mandatory")
        String city,

        @NotBlank(message = "Contact person is mandatory")
        String contactPerson,

        @NotBlank(message = "Email is mandatory")
        @Pattern(regexp = "^.+@.+\\..+$", message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone is mandatory")
        String phone,

        Boolean isActive,

        List<Theater.Room> rooms
) {
}
