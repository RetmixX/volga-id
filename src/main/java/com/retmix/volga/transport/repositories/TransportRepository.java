package com.retmix.volga.transport.repositories;

import com.retmix.volga.transport.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportRepository extends JpaRepository<Transport, Long> {
    Optional<Transport> findTransportById(Long id);
}
