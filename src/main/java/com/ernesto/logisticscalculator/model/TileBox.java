package com.ernesto.logisticscalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tile_boxes")
public class TileBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "m2_per_box")
    private Double m2PerBox;

    @Column(name = "boxes_per_pallet")
    private Integer boxesPerPallet;

    @Column(name = "weight_per_box")
    private Double weightPerBox;

    @Column(name = "deleted_ind")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "product")
    private List<TripDetail> tripDetails = new ArrayList<>();

    public TileBox(Long id, String code, String description, Double m2PerBox, Integer boxesPerPallet, Double weightPerBox) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.m2PerBox = m2PerBox;
        this.boxesPerPallet = boxesPerPallet;
        this.weightPerBox = weightPerBox;
        this.isDeleted = false;
    }


}
