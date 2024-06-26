package com.riwi.encuestas.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

// 11 
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Name must not be null")
    @Size(
            max = 100,
            message = "Name cannot be longer than 100 characters."
    )
    private String userName;

    @Email(message = "Este campo debe tener un email valido [prueba@riwi.com]")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 8, max = 255, message = "La contraseña debe tener mas de 8 caracteres")
    private String password;


    @NotNull(message = "Este campo no puede ser nulo")
    private boolean active;
}
