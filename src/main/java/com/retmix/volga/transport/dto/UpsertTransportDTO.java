package com.retmix.volga.transport.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record UpsertTransportDTO(
        @NotNull(message = "Не может быть пустым")
        Boolean canBeRented,
        @NotNull(message = "Не может быть пустым")
        String transportType,
        @NotNull(message = "Не может быть пустым")
        String model,
        @NotNull(message = "Не может быть пустым")
        String color,
        @NotNull(message = "Не может быть пустым")
        String identifier,
        @Nullable
        String description,
        @NotNull(message = "Не может быть пустым")
        Double latitude,
        @NotNull(message = "Не может быть пустым")
        Double longitude,
        @Nullable
        Double minutePrice,
        @Nullable
        Double dayPrice
) {
}
