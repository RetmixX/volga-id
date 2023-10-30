package com.retmix.volga.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnterDTO {
    private int id;
    private String username;
    private String token;
}
