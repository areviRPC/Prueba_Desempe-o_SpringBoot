package com.riwi.encuestas.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 16 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    
    @Schema(
    description = "Conteniudo de la pregunta", 
    example = "Cuantos paises hay en sur america?")                       
    @NotBlank(
    message = "la pregunta es requerida")           
    private String text;
    
    private String type;
    
    @NotNull (
    message = "El estado no puede ser nulo")            
    private boolean active;
    @NotNull(message = "opciones requeridas")
    private List<OptionQuestionRequest> optiones;
    @NotNull(message = "cuestionario requeridas")
    private int survey;
}
