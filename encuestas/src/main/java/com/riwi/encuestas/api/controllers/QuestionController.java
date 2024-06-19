package com.riwi.encuestas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.encuestas.api.dto.request.QuestionRequest;
import com.riwi.encuestas.api.dto.response.QuestionResponse;
import com.riwi.encuestas.infrastructure.abstract_services.IQuestionService;
import com.riwi.encuestas.util.enums.SortType;
import com.riwi.encuestas.util.exceptions.IdNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/questions")
@AllArgsConstructor
@Tag(name = "Questions Entity Controller")
public class QuestionController {
    
    @Autowired
    private final IQuestionService iQuestionsService;

// get all 

    @Operation(
        summary = "Muestra todas las preguntas",
        description = "Muestra todas las pregutnas con sus opciones en paginacion de 5"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getAll(
            @Parameter(description = "Numero de pagina (1 por defecto)", example = "1") 
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Numero de preguntas por pagina (5 por defecto)", example = "5") // SWAGGER
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.iQuestionsService.getAll(page - 1, size, SortType.NONE));
    }


    // get by id

    @Operation(
        summary = "Muestra una prtegunta seleccionada por id",
        description = "Muestra una pregunta seleccionada por id ingresado por el usuario."
    ) 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/{question_id}")
    public ResponseEntity<QuestionResponse> getById(
        @Parameter(description = "Question ID",example = "1") // SWAGGER
        @PathVariable Integer question_id) {

        QuestionResponse question = this.iQuestionsService.getById(question_id);
        if (question == null) {
            throw new IdNotFoundException("Question not found");
        }
        return ResponseEntity.ok(question);
    }

    // create

    @Operation(
        summary = "Crea una nueva pregunta",
        description = "crea una nueva pregunta ingresando su informacion y sus opciones"
    ) 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<QuestionResponse> create(@Validated @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(this.iQuestionsService.create(request));
    }

// update 

    @Operation(
        summary = "Actualiza una pregunta por id",
        description = "Actualiza una pregunta seleccionada por el usuairo ingresando todos sus datos incluyendo sus opciones"
    ) 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(path = "/{question_id}")
    public ResponseEntity<QuestionResponse> update(@Validated @RequestBody QuestionRequest request, 
        @Parameter(description = "User ID",example = "1") // SWAGGER
        @PathVariable Integer question_id) {
        return ResponseEntity.ok(this.iQuestionsService.update(request, question_id));
    }

}
