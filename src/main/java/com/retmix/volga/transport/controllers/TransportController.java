package com.retmix.volga.transport.controllers;

import com.retmix.volga.shared.services.UserService;
import com.retmix.volga.transport.dto.TransportDTO;
import com.retmix.volga.transport.dto.UpsertTransportDTO;
import com.retmix.volga.transport.services.TransportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transport")
@AllArgsConstructor
public class TransportController {
    private final TransportService transportService;
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<TransportDTO>> index() {
        return ResponseEntity.ok(transportService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok(transportService.show(id));
    }

    @SecurityRequirement(
            name = "bearerAuth"
    )
    @PostMapping
    public ResponseEntity<TransportDTO> create(@RequestBody @Valid UpsertTransportDTO data, @RequestHeader("authorization") String tokenRequest) {
        return ResponseEntity.status(201).body(transportService.store(data, userService.getUserByToken(tokenRequest)));
    }

    @SecurityRequirement(
            name = "bearerAuth"
    )
    @PutMapping("/{id}")
    public ResponseEntity<TransportDTO> update(@PathVariable Long id, @RequestBody @Valid UpsertTransportDTO data, @RequestHeader("authorization") String tokenRequest) {
        return ResponseEntity.ok(transportService.update(data, id, userService.getUserByToken(tokenRequest)));
    }
}
