package com.ernesto.logisticscalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "capacity_in_tons")
    private Double capacityInTons;

    @Column(name = "cost_per_trip")
    private Double cost;

    @Column(name = "deleted_ind")
    private Boolean isDeleted = false;

    public Truck(Long id, String type, Double capacityInTons, Double cost) {
        this.id = id;
        this.type = type;
        this.capacityInTons = capacityInTons;
        this.cost = cost;
        this.isDeleted = false;
    }
}
