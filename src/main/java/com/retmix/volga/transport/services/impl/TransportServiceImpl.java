package com.retmix.volga.transport.services.impl;

import com.retmix.volga.shared.models.User;
import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.transport.dto.TransportDTO;
import com.retmix.volga.transport.dto.UpsertTransportDTO;
import com.retmix.volga.transport.handler.exceptions.TransportNotFoundException;
import com.retmix.volga.transport.models.Transport;
import com.retmix.volga.transport.repositories.TransportRepository;
import com.retmix.volga.transport.repositories.TypeTransportRepository;
import com.retmix.volga.transport.services.TransportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransportServiceImpl implements TransportService {
    private final TransportRepository repositoryTransport;
    private final UserRepository userRepository;
    private final TypeTransportRepository typeTransportRepository;

    @Override
    public List<TransportDTO> index() {
        return repositoryTransport.findAll().stream().map(Transport::toDTO).toList();
    }

    @Override
    public TransportDTO show(Long id) {
        return repositoryTransport.findById(id).map(Transport::toDTO).orElseThrow(TransportNotFoundException::new);
    }

    @Override
    public TransportDTO store(UpsertTransportDTO data) {
        User user = userRepository.findAll().get(0);
        Transport transport = Transport.builder()
                .canRented(data.canBeRented())
                .type(typeTransportRepository.findTypeTransportByType(data.transportType()).orElseThrow())
                .model(data.model())
                .color(data.color())
                .identifier(data.identifier())
                .description(data.description())
                .latitude(data.latitude())
                .longitude(data.longitude())
                .minutePrice(data.minutePrice())
                .dayPrice(data.dayPrice())
                .user(user).build();

        return repositoryTransport.save(transport).toDTO();
    }

    @Override
    public TransportDTO update(UpsertTransportDTO data, Long id) {
        Transport transport = repositoryTransport.findAll().get(0);
        transport.setCanRented(data.canBeRented());
        transport.setType(typeTransportRepository.findTypeTransportByType(data.transportType()).orElseThrow());
        transport.setModel(data.model());
        transport.setColor(data.color());
        transport.setIdentifier(data.identifier());
        transport.setDescription(data.description());
        transport.setLatitude(data.latitude());
        transport.setLongitude(data.longitude());
        transport.setMinutePrice(data.minutePrice());
        transport.setDayPrice(data.dayPrice());

        return repositoryTransport.save(transport).toDTO();
    }

    @Override
    public void delete(Long id) {
        Transport transport = repositoryTransport.findTransportById(id).orElseThrow();
        repositoryTransport.delete(transport);
    }
}
