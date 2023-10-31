package com.retmix.volga.transport.services;

import com.retmix.volga.shared.models.User;
import com.retmix.volga.transport.dto.TransportDTO;
import com.retmix.volga.transport.dto.UpsertTransportDTO;

import java.util.List;

public interface TransportService {
    List<TransportDTO> index();
    TransportDTO show(Long id);
    TransportDTO store(UpsertTransportDTO data, User user);
    TransportDTO update(UpsertTransportDTO data, Long id, User user);

    void delete(Long id, User user);

}
