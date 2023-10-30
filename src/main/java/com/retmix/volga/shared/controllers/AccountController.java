package com.retmix.volga.shared.controllers;

import com.retmix.volga.shared.dto.EnterDTO;
import com.retmix.volga.shared.dto.LoginDTO;
import com.retmix.volga.shared.dto.UpsertUserDTO;
import com.retmix.volga.shared.dto.UserDTO;
import com.retmix.volga.shared.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Account")
@AllArgsConstructor
public class AccountController {
    private final UserService userService;

    @SecurityRequirement(
            name = "bearerAuth"
    )
    @GetMapping("/Me")
    public ResponseEntity<UserDTO> showMe() {
        return ResponseEntity.ok(userService.show());
    }

    @PostMapping("/SignUp")
    public ResponseEntity<EnterDTO> signUp(@RequestBody @Valid UpsertUserDTO data) {
        return ResponseEntity.status(201).body(userService.signUp(data));
    }

    @PostMapping("/SignIn")
    public ResponseEntity<EnterDTO> signIn(@RequestBody @Valid LoginDTO data) {
        return ResponseEntity.ok(userService.login(data));
    }

    @SecurityRequirement(
            name = "bearerAuth"
    )
    @PutMapping("/Update")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UpsertUserDTO data) {
        return ResponseEntity.ok(userService.update(data));
    }
}
