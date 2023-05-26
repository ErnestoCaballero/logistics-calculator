package com.ernesto.logisticscalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "travel_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    // In Kilograms
    @Column(name = "total_cargo_weight")
    private Double totalCargoWeight;

    @Column(name = "truck_capacity")
    private Double truckCapacity;

    @Column(name = "total_square_meters")
    private Double totalSquareMeters;

    @Column(name = "trip_cost")
    private Double tripCost;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripDetail> tripDetails = new ArrayList<>();

    public Trip(Long id, Date date, Truck truck, Double totalCargoWeight, Double truckCapacity, Double totalSquareMeters, Double tripCost, List<TripDetail> tripDetails) {
        this.id = id;
        this.date = date;
        this.truck = truck;
        this.totalCargoWeight = totalCargoWeight;
        this.truckCapacity = truckCapacity;
        this.totalSquareMeters = totalSquareMeters;
        this.tripCost = tripCost;
        this.tripDetails = tripDetails;
    }
}
