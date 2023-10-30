package com.retmix.volga.rent.models;

import com.retmix.volga.shared.models.User;
import com.retmix.volga.transport.models.Transport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_start")
    private LocalDateTime timeStart;
    @Column(name = "time_end")
    private LocalDateTime timeEnd;
    @Column(name = "price_of_unit")
    private double priceOfUnit;
    @Column(name = "price_type")
    private String priceType;
    @Column(name = "final_price")
    private Double finalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;
}
