package com.retmix.volga.shared.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private String username;
    private boolean role;
    private double balance;
}
