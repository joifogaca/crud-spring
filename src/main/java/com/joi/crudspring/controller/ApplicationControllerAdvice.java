package com.joi.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.joi.crudspring.exception.RecordNotFoundException;

import jakarta.validation.ConstraintViolationException;



@Validated
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationError(MethodArgumentNotValidException ex) {

        return ex.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
        .reduce("", (acc, error) -> acc + error + "\n");
}

@ExceptionHandler(ConstraintViolationException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public String handleConstraintViolationException(ConstraintViolationException ex) {
    return ex.getConstraintViolations().stream()
    .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
    .reduce("", (acc, error) -> acc + error + "\n");
}


@ExceptionHandler(MethodArgumentTypeMismatchException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    if(ex != null && ex.getRequiredType() != null){
        String type = ex.getRequiredType().getSimpleName();
        return ex.getName() + " should be of type " + type;
    }
    return "Argumento inválido: " + ex.getMessage();
    
}

}
