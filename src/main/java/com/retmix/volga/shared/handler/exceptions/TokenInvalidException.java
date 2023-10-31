package com.retmix.volga.shared.handler.exceptions;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }

    public TokenInvalidException() {
        super("Invalid token");
    }
}
