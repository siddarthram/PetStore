package com.petstore.demo.controller;

import com.petstore.demo.exception.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetExceptionController {

    @ExceptionHandler(value = PetNotFoundException.class)
    private ResponseEntity<Object> exception(PetNotFoundException exception){
        return new ResponseEntity<>("Pet not found", HttpStatus.NOT_FOUND);
    }
}
