package com.retmix.volga.transport.services;

import com.retmix.volga.shared.handler.exceptions.ObjectNotFoundException;
import com.retmix.volga.shared.models.User;
import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.transport.dto.admin.AdminTransportDTO;
import com.retmix.volga.transport.dto.admin.AdminUpsertTransportDTO;
import com.retmix.volga.transport.models.Transport;
import com.retmix.volga.transport.repositories.TransportRepository;
import com.retmix.volga.transport.repositories.TypeTransportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminTransportService {
    private final TransportRepository transportRepository;
    private final TypeTransportRepository typeTransportRepository;
    private final UserRepository userRepository;

    public List<AdminTransportDTO> index() {
        return transportRepository.findAll().stream().map(Transport::toAdminTransportDTO).toList();
    }

    public AdminTransportDTO show(Long id) {
        return transportRepository.findById(id).map(Transport::toAdminTransportDTO)
                .orElseThrow(() -> new ObjectNotFoundException("Transport not found"));
    }

    public AdminTransportDTO create(AdminUpsertTransportDTO data) {
        User user = userRepository.findById(data.getOwnerId()).get();

        Transport transport = Transport.builder()
                .canRented(data.getCanBeRented())
                .type(typeTransportRepository.findTypeTransportByType(data.getTransportType()).orElseThrow(()-> new ObjectNotFoundException("Type transport not found")))
                .model(data.getModel())
                .color(data.getColor())
                .identifier(data.getIdentifier())
                .description(data.getDescription())
                .latitude(data.getLatitude())
                .longitude(data.getLongitude())
                .minutePrice(data.getMinutePrice())
                .dayPrice(data.getDayPrice())
                .user(user).build();

        return transportRepository.save(transport).toAdminTransportDTO();
    }

    public AdminTransportDTO update(Long id, AdminUpsertTransportDTO data) {
        User user = userRepository.findById(data.getOwnerId()).get();
        Transport transport = transportRepository.findTransportById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Transport not found"));

        transport.setCanRented(data.getCanBeRented());
        transport.setType(typeTransportRepository.findTypeTransportByType(data.getTransportType()).orElseThrow(() -> new ObjectNotFoundException("Type ts not found")));
        transport.setModel(data.getModel());
        transport.setColor(data.getColor());
        transport.setIdentifier(data.getIdentifier());
        transport.setDescription(data.getDescription());
        transport.setLatitude(data.getLatitude());
        transport.setLongitude(data.getLongitude());
        transport.setMinutePrice(data.getMinutePrice());
        transport.setDayPrice(data.getDayPrice());
        transport.setUser(user);

        return transportRepository.save(transport).toAdminTransportDTO();
    }

    public void delete(Long id) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Transport not found"));

        transportRepository.delete(transport);
    }
}
