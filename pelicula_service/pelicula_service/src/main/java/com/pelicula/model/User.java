package com.pelicula.model;

import com.pelicula.enums.Role;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

//    @Indexed(unique = true)
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^.+@.+\\..+$", message = "Invalid email format")
    private String email;

    private String password;

    @NotNull(message = "User's role - admin, client, or employee")
    private Role role;

    private Boolean status ;

    private Date registrationDate;

    private Date lastAccess;






    // Getters y Setters
    public String getRegistrationDate() {
        return formatDateToString(registrationDate);
    }

    public String getLastAccess() {
        return formatDateToString(lastAccess);
    }

    private String formatDateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
