package com.retmix.volga.shared.handler;

import com.retmix.volga.shared.dto.MessageDTO;
import com.retmix.volga.shared.dto.ValidationErrorDTO;
import com.retmix.volga.shared.handler.exceptions.ObjectNotFoundException;
import com.retmix.volga.shared.handler.exceptions.PermissionException;
import com.retmix.volga.shared.handler.exceptions.TokenInvalidException;
import com.retmix.volga.shared.handler.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet())
                .stream().map(error-> Map.of(error, ex.getFieldErrors(error)
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList()))
                .toList();
        return ResponseEntity.status(422)
                .body(new ValidationErrorDTO(errors));
    }

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<MessageDTO> tokenInvalidException(TokenInvalidException ex) {
        return ResponseEntity.status(401).body(new MessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageDTO> userNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(401).body(new MessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<MessageDTO> permissionException(PermissionException ex) {
        return ResponseEntity.status(403).body(new MessageDTO(ex.getMessage()));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<MessageDTO> objectNotFoundException(ObjectNotFoundException ex) {
        return ResponseEntity.status(404).body(new MessageDTO(ex.getMessage()));
    }
}
