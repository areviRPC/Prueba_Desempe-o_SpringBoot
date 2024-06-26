package com.riwi.encuestas.api.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 10 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @Schema(description = "ID of the user")         // SWAGGER
    private int id;
    @Schema(description = "name of the user")       // SWAGGER
    private String userName;
    @Schema(description = "Email of the user")      // SWAGGER
    private String email;
    @Schema(description = "State of the user")      // SWAGGER
    private boolean active;
    
    @Schema(description = "List of surveys related to the user") // SWAGGER
    private List<SurveyResponseInUser> surveys;
}   