package com.retmix.volga.shared.handler;

import com.retmix.volga.shared.dto.MessageDTO;
import com.retmix.volga.shared.dto.ValidationErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessageDTO> usernameNotFountException(UsernameNotFoundException ex) {
        return ResponseEntity.status(403).body(new MessageDTO(ex.getMessage()));
    }

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
