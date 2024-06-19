package com.riwi.encuestas.api.dto.request;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//15
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest {

    @Schema(
    description = "Nombre del usaurio creador de la encuesta", 
    example = "Carlos_R")                      
    @NotBlank(
    message = "El nombre es obligatorio")           
    @Size(
    max = 50, 
    message = "El nombre debe contener maximo 50 caracteres")   
    private String title; 

    private String description;

    private Date creationDate;
    @NotNull (
    message = "El estado no puede ser nulo (TRUE / FALSE)")

    private boolean active;

    private int Userid;
}
