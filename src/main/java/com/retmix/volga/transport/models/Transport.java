package com.retmix.volga.transport.models;

import com.retmix.volga.rent.models.Rent;
import com.retmix.volga.shared.models.User;
import com.retmix.volga.transport.dto.TransportDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transports")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "can_rented")
    private boolean canRented;
    private String model;
    private String color;
    private String identifier;
    private String description;
    private double latitude;
    private double longitude;
    @Column(name = "minute_price")
    private double minutePrice;
    @Column(name = "day_price")
    private double dayPrice;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "transport_type_id")
    private TypeTransport type;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transport")
    private List<Rent> rents;

    public TransportDTO toDTO() {
        return TransportDTO.builder()
                .id(id)
                .canBeRented(canRented)
                .transportType(type.getType())
                .model(model)
                .color(color)
                .identifier(identifier)
                .description(description)
                .latitude(latitude)
                .longitude(longitude)
                .minutePrice(minutePrice)
                .dayPrice(dayPrice).build();

    }

}
