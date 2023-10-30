package com.retmix.volga.transport.handler;

import com.retmix.volga.shared.dto.MessageDTO;
import com.retmix.volga.transport.handler.exceptions.TransportNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransportHandler {

    @ExceptionHandler(TransportNotFoundException.class)
    public ResponseEntity<MessageDTO> transportNotFound(TransportNotFoundException ex) {
        return ResponseEntity.status(404).body(new MessageDTO(ex.getMessage()));
    }
}
