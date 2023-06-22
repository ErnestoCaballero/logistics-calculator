package com.ernesto.logisticscalculator.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TruckCommand {
    private Long id;
    private String type;
    private Double capacityInTons;
    private Double cost;
}
