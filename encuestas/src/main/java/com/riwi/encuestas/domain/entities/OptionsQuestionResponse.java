package com.riwi.encuestas.domain.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//17
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionsQuestionResponse {
    @Schema(description = "Id de la opcion") 
    private int idOptionQuestion;

    @Schema(description = "opcion de la pregunta") 
    private String text;

    @Schema(description = "estado") 
    private boolean active;

    @Schema(description = "Id de la pregunta relacionada") 
    private int idQuestion;
}
