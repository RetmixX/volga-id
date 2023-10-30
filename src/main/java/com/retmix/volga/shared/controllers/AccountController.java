package com.retmix.volga.shared.controllers;

import com.retmix.volga.shared.dto.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @GetMapping("/Me")
    public ResponseEntity<?> showMe() {
        return null;
    }

    @PostMapping("/SignUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid LoginDTO data) {
        return ResponseEntity.ok("ok");
    }
}
