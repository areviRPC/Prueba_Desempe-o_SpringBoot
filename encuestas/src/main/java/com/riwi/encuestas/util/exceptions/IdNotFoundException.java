package com.riwi.encuestas.util.exceptions;

// 3
public class IdNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE = "No se encuentra la entidad %s con el id suministrado";

    public IdNotFoundException(String nameEntity){
        super(String.format(ERROR_MESSAGE,nameEntity));
    }
}
