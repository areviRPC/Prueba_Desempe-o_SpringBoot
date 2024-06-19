package com.riwi.encuestas.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//17
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestionRequest {
    @Schema(
    description = "La respuesta de la pregunta es requerida", 
    example = "respuesta")                       
    @NotBlank(
    message = "La pregunta es requerida")           
    private String text;

    @NotNull (
    message = "El estado no peude ser nulo")           
    private boolean active;

}
