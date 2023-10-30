package com.retmix.volga.transport.dto;

import com.retmix.volga.transport.models.Transport;
import lombok.Builder;

@Builder
public record TransportDTO(
        Long id,
        Boolean canBeRented,
        String transportType,
        String model,
        String color,
        String identifier,
        String description,
        Double latitude,
        Double longitude,
        Double minutePrice,
        Double dayPrice
) {
    public Transport toModel() {
        return Transport.builder()
                .id(id)
                .canRented(canBeRented)
                .model(model)
                .color(color)
                .identifier(identifier)
                .description(description)
                .latitude(latitude)
                .longitude(longitude)
                .minutePrice(minutePrice)
                .dayPrice(dayPrice)
                .build();

    }
}
