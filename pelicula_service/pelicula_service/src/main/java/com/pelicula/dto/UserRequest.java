package com.pelicula.dto;

import com.pelicula.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

public record UserRequest(
        String id,

        @NotBlank(message = "Name is mandatory")
        String name,

//        @Indexed(unique = true)
        @NotBlank(message = "Email is mandatory")
        @Pattern(regexp = "^.+@.+\\..+$", message = "Invalid email format")
        String email,

        String password,

        @NotNull(message = "User's role - admin, client, or employee")
        Role role,

        Boolean status,

        Date registrationDate,

        Date lastAccess

        ) {
}
