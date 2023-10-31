package com.retmix.volga.shared.dto.admin;

import com.retmix.volga.utils.validators.UsernameNotExist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertAccountDTO {
    @NotNull
    @UsernameNotExist
    private String username;
    @NotNull
    private String password;
    private boolean isAdmin;
    private double balance;
}
