package com.retmix.volga.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EnterDTO {
    private Long id;
    private String username;
    private String token;
}
