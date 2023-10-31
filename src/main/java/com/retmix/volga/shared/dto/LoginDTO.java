package com.retmix.volga.shared.dto;

import com.retmix.volga.utils.validators.UsernameNotExist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
