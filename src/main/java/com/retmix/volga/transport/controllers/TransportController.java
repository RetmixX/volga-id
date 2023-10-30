package com.retmix.volga.transport.controllers;

import com.retmix.volga.transport.dto.TransportDTO;
import com.retmix.volga.transport.dto.UpsertTransportDTO;
import com.retmix.volga.transport.services.TransportService;
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

    @GetMapping
    public ResponseEntity<List<TransportDTO>> index() {
        return ResponseEntity.ok(transportService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok(transportService.show(id));
    }

    @PostMapping
    public ResponseEntity<TransportDTO> create(@RequestBody @Valid UpsertTransportDTO data) {
        return ResponseEntity.status(201).body(transportService.store(data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportDTO> update(@PathVariable Long id, @RequestBody @Valid UpsertTransportDTO data) {
        return ResponseEntity.ok(transportService.update(data, id));
    }
}
