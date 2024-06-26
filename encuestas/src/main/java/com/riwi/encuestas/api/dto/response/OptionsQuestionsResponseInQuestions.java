package com.riwi.encuestas.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionsQuestionsResponseInQuestions {

    @Schema(description = "ID of the OptionQuestion") // SWAGGER
    private int idOptionQuestion;
    @Schema(description = "OptionQuestion") // SWAGGER
    private String text;
    @Schema(description = "state") // SWAGGER
    private boolean active;
    @Schema(description = "ID of the Question") // SWAGGER
    private int idQuestion;

    
}
