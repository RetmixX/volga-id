package com.retmix.volga.transport.handler.exceptions;

public class TransportNotFoundException extends RuntimeException {
    public TransportNotFoundException(String message) {
        super(message);
    }

    public TransportNotFoundException() {
        super("Транспорт не найден");
    }
}
