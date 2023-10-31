package com.retmix.volga.shared.controllers;

import com.retmix.volga.shared.dto.admin.UpsertAccountDTO;
import com.retmix.volga.shared.models.Role;
import com.retmix.volga.shared.models.User;
import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.shared.services.AdminAccountService;
import com.retmix.volga.shared.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
@RequestMapping("/Admin/Account")
public class AdminAccountController {
    private final UserService userService;
    private final AdminAccountService adminAccountService;

    @GetMapping
    public ResponseEntity<?> index(@RequestHeader("authorization") String tokenRequest) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        return ResponseEntity.ok(adminAccountService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id, @RequestHeader("authorization") String tokenRequest) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));

        return ResponseEntity.ok(adminAccountService.show(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UpsertAccountDTO data, @RequestHeader("authorization") String tokenRequest) throws NoSuchAlgorithmException {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        return ResponseEntity.status(201).body(adminAccountService.createAccount(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpsertAccountDTO data, @PathVariable Long id, @RequestHeader("authorization") String tokenRequest) throws NoSuchAlgorithmException {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        return ResponseEntity.ok(adminAccountService.updateAccount(data, id));
    }
}
