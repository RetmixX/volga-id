package com.retmix.volga.shared.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class Handler {

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet())
                .stream().map(error-> Map.of(error, ex.getFieldErrors(error)
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList()))
                .toList();
        return ResponseEntity.status(422)
                .body(new ValidationErrorDTO(errors));
    }*/
}
