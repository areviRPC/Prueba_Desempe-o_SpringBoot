package com.riwi.encuestas.api.dto.response;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 15
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponse {
    @Schema(description = "Titulo de la encuesta") 
    private String title;
    @Schema(description = "Descripcion de la encuesta") 
    private String description;
    @Schema(description = "Fecha de creacion de la encuesta") 
    private Date creationDate;
    @Schema(description = "Estado de la encuesta activa por defecto") 
    private boolean active;
}
