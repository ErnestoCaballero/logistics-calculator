package com.ernesto.logisticscalculator.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trip_details")
public class TripDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(name = "sent_boxes")
    private Integer sentBoxes;

    @Column(name = "total_square_meters")
    private Double totalSquareMeters;

    @Column(name = "total_weight")
    private Double totalWeight;

    

}
