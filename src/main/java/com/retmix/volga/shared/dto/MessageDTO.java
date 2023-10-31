package com.retmix.volga.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageDTO {
    private String message;

    @Override
    public String toString() {
        return "{" +
                "\"message\": " + "\"" + message + "\"" +
                '}';
    }
}
