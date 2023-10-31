package com.retmix.volga.shared.controllers;

import com.retmix.volga.shared.models.User;
import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.shared.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Payment/Hesoyam")
@AllArgsConstructor
public class PaymentController {
    private final UserService userService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{id}")
    public ResponseEntity<?> hesoyam(@PathVariable Long id, @RequestHeader("authorization") String tokenRequest) {
        return ResponseEntity.ok(userService.changeBalance(id, userService.getUserByToken(tokenRequest)));
    }
}
