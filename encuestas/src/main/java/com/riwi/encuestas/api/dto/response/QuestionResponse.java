package com.riwi.encuestas.api.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// 16
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    @Schema(description = "Id de la pregunta") 
    private int idQuestion;
    @Schema(description = "texto de la pregunta") 
    private String text;
    @Schema(description = "Tipo de pregunta") 
    private String type;
    @Schema(description = "Estado de la pregunta") 
    private boolean active;
    @Schema(description = "Option Question in Question")
    private List <OptionsQuestionsResponseInQuestions> optionQuestions;
}
