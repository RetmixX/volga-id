package com.retmix.volga.rent.repositories;

import com.retmix.volga.rent.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    Optional<Rent> findRentById(Long id);
}
