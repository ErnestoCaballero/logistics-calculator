package com.ernesto.logisticscalculator.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TripDetailCommand {
    private Long id;
    private TripCommand tripCommand;
    private TileBoxCommand tileBoxCommand;
    private String productName;
    private Integer sentBoxes;
    private Double totalSquareMeters;
    private Double totalWeight;
}
