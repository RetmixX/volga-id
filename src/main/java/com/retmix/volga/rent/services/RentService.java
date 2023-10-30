package com.retmix.volga.rent.services;

import com.retmix.volga.rent.dto.RentDTO;
import com.retmix.volga.rent.dto.UpsertRentDTO;

import java.util.List;

public interface RentService {
    List<RentDTO> index();
    RentDTO show(Long id);
    RentDTO update(UpsertRentDTO data, Long id);
    RentDTO store(UpsertRentDTO data);
}
