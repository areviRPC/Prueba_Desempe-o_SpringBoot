package com.riwi.encuestas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.encuestas.api.dto.request.SurveyRequest;
import com.riwi.encuestas.api.dto.response.SurveyResponse;
import com.riwi.encuestas.infrastructure.abstract_services.ISurveyService;
import com.riwi.encuestas.util.enums.SortType;
import com.riwi.encuestas.util.exceptions.IdNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/surveys")
@AllArgsConstructor
@Tag(name = "Survey Entity Controller")

public class SurveyController {
    @Autowired
    private final ISurveyService iSurveyService;

    // get all

    @Operation(
        summary = "Mostrar todas las encuestas",
        description = "Muestra todas las encuestas mostrando 10 por pagina por defecto"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Page<SurveyResponse>> getAll(
            @Parameter(description = "Numero de paginas (por defecto 1)", example = "1") 
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Numero de  items por pagina (10 por defecto)", example = "10") 
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iSurveyService.getAll(page - 1, size, SortType.NONE));
    }

    // get by id

    @Operation(
        summary = "Muestra una encuesta por id",
        description = "Muestra una encuesta seleccionada por su id, el cual es ingresado por el usuario"
    ) 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/{survey_id}")
    public ResponseEntity<SurveyResponse> getById(
        @Parameter(description = "Survey ID",example = "1") 
        @PathVariable Integer survey_id) {

        SurveyResponse survey = this.iSurveyService.getById(survey_id);
        if (survey == null) {
            throw new IdNotFoundException("id de la encuesta no encontrado");
        }
        return ResponseEntity.ok(survey);
    }

// crear

    @Operation(
        summary = "crear una nueva encuesta",
        description = "crea una nueva encuesta pidiendo al usaurio completar todos los datos del request"
    ) //SWAGGER
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<SurveyResponse> create(@Validated @RequestBody SurveyRequest request) {
        return ResponseEntity.ok(this.iSurveyService.create(request));
    }

}
