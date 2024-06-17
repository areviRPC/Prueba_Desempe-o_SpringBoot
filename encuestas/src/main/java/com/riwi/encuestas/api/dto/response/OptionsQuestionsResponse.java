package com.riwi.encuestas.api.dto.response;


import com.riwi.encuestas.domain.entities.Question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionsQuestionsResponse {

    @Schema(description = "ID of the OptionQuestion") // SWAGGER
    private int idOptionQuestion;
    @Schema(description = "OptionQuestion") // SWAGGER
    private String text;
    @Schema(description = "type option") // SWAGGER
    private String type;
    @Schema(description = "state") // SWAGGER
    private boolean active;
    @Schema(description = "question") // SWAGGER
    private Question question;
    
}
