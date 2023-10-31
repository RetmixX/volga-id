package com.retmix.volga.transport.dto.admin;

import com.retmix.volga.shared.repositories.UserRepository;
import com.retmix.volga.utils.validators.IdExist;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpsertTransportDTO implements Serializable {
    @NotNull(message = "Не может быть пустым") Boolean canBeRented;
    @NotNull(message = "Не может быть пустым") String transportType;
    @NotNull(message = "Не может быть пустым") String model;
    @NotNull(message = "Не может быть пустым") String color;
    @NotNull(message = "Не может быть пустым") String identifier;
    @Nullable
    String description;
    @NotNull(message = "Не может быть пустым") Double latitude;
    @NotNull(message = "Не может быть пустым") Double longitude;
    @Nullable
    Double minutePrice;
    @Nullable
    Double dayPrice;
    @IdExist(
            repository = UserRepository.class,
            message = "User not found"
    )
    @NotNull Long ownerId;
}
