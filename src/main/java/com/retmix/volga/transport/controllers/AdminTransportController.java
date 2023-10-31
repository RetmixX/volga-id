package com.retmix.volga.transport.controllers;

import com.retmix.volga.shared.dto.MessageDTO;
import com.retmix.volga.shared.services.AdminAccountService;
import com.retmix.volga.shared.services.UserService;
import com.retmix.volga.transport.dto.admin.AdminTransportDTO;
import com.retmix.volga.transport.dto.admin.AdminUpsertTransportDTO;
import com.retmix.volga.transport.services.AdminTransportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Admin/Transport")
public class AdminTransportController {
    private final UserService userService;
    private final AdminAccountService adminAccountService;
    private final AdminTransportService adminTransportService;

    @GetMapping
    public ResponseEntity<List<AdminTransportDTO>> index(@RequestHeader("authorization") String tokenRequest) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        return ResponseEntity.ok(adminTransportService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminTransportDTO> show(@RequestHeader("authorization") String tokenRequest, @PathVariable Long id) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        return ResponseEntity.ok(adminTransportService.show(id));
    }

    @PostMapping
    public ResponseEntity<AdminTransportDTO> create(@RequestHeader("authorization") String tokenRequest, AdminUpsertTransportDTO data) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));

        return ResponseEntity.status(201).body(adminTransportService.create(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminTransportDTO> update(@RequestHeader("authorization") String tokenRequest, @PathVariable Long id, AdminUpsertTransportDTO data) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        return ResponseEntity.ok(adminTransportService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@RequestHeader("authorization") String tokenRequest, @PathVariable Long id) {
        adminAccountService.isAdmin(userService.getUserByToken(tokenRequest));
        adminTransportService.delete(id);
        return ResponseEntity.ok(new MessageDTO("Transport deleted"));
    }
}
