package com.retmix.volga.shared.dto;

import java.util.List;
import java.util.Map;


public class ValidationErrorDTO {
    private String message;
    private List<Map<String, List<String>>> errors;

    public ValidationErrorDTO(List<Map<String, List<String>>> errors) {
        this.message = "Validation error";
        this.errors = errors;
    }
}
