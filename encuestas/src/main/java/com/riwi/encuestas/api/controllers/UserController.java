package com.riwi.encuestas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.riwi.encuestas.api.dto.request.UserRequest;
import com.riwi.encuestas.api.dto.response.UserResponse;
import com.riwi.encuestas.infrastructure.abstract_services.IUserService;
import com.riwi.encuestas.util.enums.SortType;
import com.riwi.encuestas.util.exceptions.IdNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Tag(name = "User entity Controller")
public class UserController {
    @Autowired
    private final IUserService iUserService;

// get all

     @Operation(
        summary = "Mostrar usuarios",
        description = "Muestra todos los usarios en lista mostrando 10 por pagina."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @Parameter(description = "Numero de la pagina por defecto", example = "1") 
            @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Number de items por pagina", example = "10") 
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(this.iUserService.getAll(page - 1, size, SortType.NONE));
    }


// get by id

    @Operation(
        summary = "Mostrar usuarios por id",
        description = "Muestra el usuario por un id suministrado"
    ) 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<UserResponse> getById(
        @Parameter(description = "User ID",example = "1") // SWAGGER
        @PathVariable Integer user_id) {

        UserResponse user = this.iUserService.getById(user_id);
        if (user == null) {
            throw new IdNotFoundException("User not found");
        }
        return ResponseEntity.ok(user);
    }

// crear usuario 

    @Operation(
        summary = "creates a new user",
        description = "create a new user by entering the required data"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<UserResponse> create( @Validated @RequestBody UserRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(this.iUserService.create(request));
    }

// Actualizar usuario

    @Operation(
        summary = "Actualizar usuairo",
        description = "Actualiza el usuario con los datos infresados seleccionando su id"
    ) 
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "SUCCESSFUL"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "401", description = "NOT AUTHORIZED"),
        @ApiResponse(responseCode = "403", description = "FORBIDDEN ACCESS"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(path = "/{user_id}")
    public ResponseEntity<UserResponse> update(@Validated @RequestBody UserRequest request,
        @Parameter(description = "User ID",example = "1") // SWAGGER
        @PathVariable Integer user_id) {
        return ResponseEntity.ok(this.iUserService.update(request, user_id));
    }
}
