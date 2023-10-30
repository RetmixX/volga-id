package com.retmix.volga.transport.repositories;

import com.retmix.volga.transport.models.TypeTransport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeTransportRepository extends JpaRepository<TypeTransport, Integer> {
    Optional<TypeTransport> findTypeTransportByType(String type);
}
